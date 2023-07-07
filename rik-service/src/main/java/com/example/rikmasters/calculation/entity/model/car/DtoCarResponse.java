package com.example.rikmasters.calculation.entity.model.car;

import com.example.rikmasters.calculation.entity.Detail;
import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.entity.model.user.DtoUserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoCarResponse {
    private DtoUserResponse owner;
    private String vin;
    private String stateNumber;
    private Set<Detail> details;
    private AccountStatus accountStatus;
}
