package com.example.rikmasters.calculation.service.impl;

import com.example.rikmasters.calculation.entity.constant.ActionStatus;
import com.example.rikmasters.calculation.entity.constant.CashColor;
import com.example.rikmasters.calculation.entity.mapper.CashAccountMapper;
import com.example.rikmasters.calculation.entity.model.CashAccountResponse;
import com.example.rikmasters.calculation.repository.CashAccountRepositoryCustom;
import com.example.rikmasters.calculation.service.CashService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CashServiceImpl implements CashService {
    private final CashAccountMapper cashAccountMapper;
    private final CashAccountRepositoryCustom cashAccountRepositoryCustom;

    @Autowired
    public CashServiceImpl(CashAccountMapper cashAccountMapper, CashAccountRepositoryCustom cashAccountRepositoryCustom) {
        this.cashAccountMapper = cashAccountMapper;
        this.cashAccountRepositoryCustom = cashAccountRepositoryCustom;
    }

    @Override
    public CashAccountResponse createCashAccount(Long id) {
        cashAccountRepositoryCustom.createCashAccount(id);
        return cashAccountMapper.toCashAccountResponse(cashAccountRepositoryCustom.getCash(id));
    }

    @Override
    public CashAccountResponse sendCash(final Long id, final BigDecimal sum, final CashColor cashColor, final ActionStatus actionStatus) {
        cashAccountRepositoryCustom.sendCash(id, sum, cashColor, actionStatus);
        return cashAccountMapper.toCashAccountResponse(cashAccountRepositoryCustom.getCash(id));
    }

    @Override
    public CashAccountResponse getCash(final Long id) {
        return cashAccountMapper.toCashAccountResponse(cashAccountRepositoryCustom.getCash(id));
    }

    @Scheduled(fixedDelay = 10000)
    void getTodayBirthdayDrivers() {
        List<String> names = cashAccountRepositoryCustom.getTodayBirthdayDrivers();
        if (names.size() != 0) {
            for (String name : names) {
                log.warn(String.format("happyBirthday: %s", name));
            }
        }
    }
}