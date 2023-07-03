package com.example.rikmasters.service.entity.mapper;

import com.example.rikmasters.service.entity.LicenseCategory;
import com.example.rikmasters.service.entity.model.licernse.DtoLicenseCategoryShort;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LicenseCategoryMapper {
    DtoLicenseCategoryShort toDtoLicenseCategoryShort(LicenseCategory licenseCategory);
}
