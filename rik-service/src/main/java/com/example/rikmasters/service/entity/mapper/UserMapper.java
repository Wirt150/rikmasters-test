package com.example.rikmasters.service.entity.mapper;

import com.example.rikmasters.service.entity.User;
import com.example.rikmasters.service.entity.model.user.DtoUserRequest;
import com.example.rikmasters.service.entity.model.user.DtoUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = LicenseCategoryMapper.class)
public interface UserMapper {
    User toUser(DtoUserRequest dtoUserRequest);

    DtoUserResponse toDtoUserResponse(User user);

    List<DtoUserResponse> DtoUserResponses(List<User> users);

}
