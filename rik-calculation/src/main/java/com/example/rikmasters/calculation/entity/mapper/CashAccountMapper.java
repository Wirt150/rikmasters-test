package com.example.rikmasters.calculation.entity.mapper;

import com.example.rikmasters.calculation.entity.CashAccount;
import com.example.rikmasters.calculation.entity.model.CashAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CashAccountMapper {
    CashAccountResponse toCashAccountResponse(CashAccount cashAccount);
}
