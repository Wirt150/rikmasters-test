package com.example.rikmasters.calculation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashAccount {
    private Long id;
    private Long owner;
    private BigDecimal redDollar;
    private BigDecimal greenDollar;
    private BigDecimal blueDollar;
}
