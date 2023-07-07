package com.example.rikmasters.calculation.entity.mapper;

import com.example.rikmasters.calculation.entity.LicenseCategory;
import com.example.rikmasters.calculation.entity.User;
import com.example.rikmasters.calculation.entity.model.user.DtoUserRequest;
import com.example.rikmasters.calculation.entity.model.user.DtoUserResponse;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = LicenseCategoryMapper.class)
public interface UserMapper {

    @Mapping(target = "userLicense", source = "userLicense", qualifiedByName = "userLicenseMapping")
    User toUser(DtoUserRequest dtoUserRequest);

    DtoUserResponse toDtoUserResponse(User user);

    List<DtoUserResponse> toDtoUserResponses(List<User> users);

    @Named("userLicenseMapping")
    default Set<LicenseCategory> userLicenseMapping(Set<Long> userLicenseId) {
        return userLicenseId.stream()
                .map(id -> LicenseCategory.builder().id(id).build())
                .collect(Collectors.toSet());
    }

}
