package com.example.rikmasters.calculation.repository.custom;

import com.example.rikmasters.calculation.entity.CashAccount;
import com.example.rikmasters.calculation.entity.constant.ActionStatus;
import com.example.rikmasters.calculation.entity.constant.CashColor;
import com.example.rikmasters.calculation.repository.CashAccountRepositoryCustom;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.*;

@Repository
@Transactional
public class CashAccountRepositoryCustomImpl implements CashAccountRepositoryCustom {

    private static final String SQL_CREATE_CASH = "" +
            "INSERT INTO cash_account(owner, red_dollar, green_dollar, blue_dollar) " +
            "VALUES (:id, 0,0,0);";
    private static final String SQL_UPDATE_CASH = "" +
            "UPDATE cash_account " +
            "SET red_dollar = red_dollar {0} :red, " +
            "    green_dollar = green_dollar {0} :green, " +
            "    blue_dollar = blue_dollar {0} :blue " +
            "WHERE id = :id;";
    private static final String SQL_SELECT_CASH = "" +
            "SELECT id, owner, red_dollar, green_dollar, blue_dollar " +
            "FROM cash_account WHERE id = :id;";
    private static final String SQL_SELECT_BIRTHDAY = "" +
            "SELECT full_name " +
            "FROM users " +
            "WHERE :day = EXTRACT (DAY FROM birthday) AND :month = EXTRACT (MONTH FROM birthday);";
    private static final int PLUS_MONTH = 1;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CashAccountRepositoryCustomImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void sendCash(Long id, BigDecimal sum, CashColor cashColor, ActionStatus actionStatus) {
        Map<CashColor, BigDecimal> cashColorBigDecimalMap = this.calculations(sum, cashColor);

        String action = switch (actionStatus) {
            case SUBTRACTION -> "-";
            case ADDITION -> "+";
        };

        String sql = MessageFormat.format(SQL_UPDATE_CASH, action);
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue("red", cashColorBigDecimalMap.get(CashColor.RED));
        inQueryParams.addValue("green", cashColorBigDecimalMap.get(CashColor.GREEN));
        inQueryParams.addValue("blue", cashColorBigDecimalMap.get(CashColor.BLUE));
        inQueryParams.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, inQueryParams);
    }

    @Override
    public CashAccount getCash(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                SQL_SELECT_CASH, new MapSqlParameterSource().addValue("id", id), new CashAccountRowMapper());
    }

    @Override
    public List<String> getTodayBirthdayDrivers() {
        Calendar calendar = Calendar.getInstance();
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue("day", calendar.get(Calendar.DAY_OF_MONTH));
        inQueryParams.addValue("month", calendar.get(Calendar.MONTH) + PLUS_MONTH);
        namedParameterJdbcTemplate.queryForList(SQL_SELECT_BIRTHDAY, inQueryParams, String.class);
        return namedParameterJdbcTemplate.queryForList(SQL_SELECT_BIRTHDAY, inQueryParams, String.class);
    }

    @Override
    public int createCashAccount(final Long id) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue("id", id);
        namedParameterJdbcTemplate.update(SQL_CREATE_CASH, inQueryParams, keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    private Map<CashColor, BigDecimal> calculations(final BigDecimal sum, final CashColor cashColor) {
        Map<CashColor, BigDecimal> calculations = new HashMap<>();
        switch (cashColor) {
            case RED -> {
                calculations.put(CashColor.RED, sum);
                calculations.put(CashColor.GREEN, sum.multiply(new BigDecimal("2.5")));
                calculations.put(CashColor.BLUE, sum.multiply(new BigDecimal("1.5")));
            }
            case GREEN -> {
                calculations.put(CashColor.RED, sum.divide(new BigDecimal("2.5"), 10, RoundingMode.HALF_EVEN));
                calculations.put(CashColor.GREEN, sum);
                calculations.put(CashColor.BLUE, sum.multiply(new BigDecimal("0.6")));
            }
            case BLUE -> {
                calculations.put(CashColor.RED, sum.multiply(new BigDecimal("0.6666666667")));
                calculations.put(CashColor.GREEN, sum.divide(new BigDecimal("0.6"), 10, RoundingMode.HALF_EVEN));
                calculations.put(CashColor.BLUE, sum);
            }
        }
        return calculations;
    }

    static class CashAccountRowMapper implements RowMapper<CashAccount> {
        @Override
        public CashAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
            return CashAccount.builder()
                    .id(rs.getLong("id"))
                    .owner(rs.getLong("owner"))
                    .redDollar(rs.getBigDecimal("red_dollar"))
                    .greenDollar(rs.getBigDecimal("green_dollar"))
                    .blueDollar(rs.getBigDecimal("blue_dollar"))
                    .build();
        }
    }
}



