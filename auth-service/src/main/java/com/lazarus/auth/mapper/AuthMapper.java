package com.lazarus.auth.mapper;

import com.lazarus.auth.dto.auth.LoginResponse;
import com.lazarus.auth.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "message", constant = "Login successful")
    LoginResponse toLoginResponse(UserAccount userAccount);
}
