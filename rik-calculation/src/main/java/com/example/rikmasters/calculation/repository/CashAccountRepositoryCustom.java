package com.example.rikmasters.calculation.repository;

import com.example.rikmasters.calculation.entity.CashAccount;
import com.example.rikmasters.calculation.entity.constant.ActionStatus;
import com.example.rikmasters.calculation.entity.constant.CashColor;

import java.math.BigDecimal;
import java.util.List;

public interface CashAccountRepositoryCustom {

    int createCashAccount(Long id);

    void sendCash(Long id, BigDecimal sum, CashColor cashColor, ActionStatus actionStatus);

    CashAccount getCash(Long id);

    List<String> getTodayBirthdayDrivers();
}
