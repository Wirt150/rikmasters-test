package com.example.rikmasters.calculation.service;


import com.example.rikmasters.calculation.entity.constant.ActionStatus;
import com.example.rikmasters.calculation.entity.constant.CashColor;
import com.example.rikmasters.calculation.entity.model.CashAccountResponse;

import java.math.BigDecimal;

public interface CashService {
    CashAccountResponse sendCash(Long id, BigDecimal sum, CashColor cashColor, ActionStatus actionStatus);

    CashAccountResponse getCash(Long id);

    CashAccountResponse createCashAccount(Long id);
}
