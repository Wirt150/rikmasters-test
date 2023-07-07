package com.example.rikmasters.calculation.entity.model.user;

import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.entity.model.licernse.DtoLicenseCategoryShort;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserResponse {
    private String fullName;
    private String passport;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer drivingExperience;
    private Set<DtoLicenseCategoryShort> userLicense;
    private AccountStatus accountStatus;

}
