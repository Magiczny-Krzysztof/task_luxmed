package com.task.converter;

import com.task.dto.GitApiResponseDto;
import com.task.dto.UserDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(GitApiResponseDtoToUserDtoDecorator.class)
public interface GitApiResponseDtoToUserDto {

    @Mapping(source = "avatar_url", target = "avatarUrl")
    @Mapping(source = "created_at", target = "createdAt")
    UserDto mapToUserDto(GitApiResponseDto gitApiResponseDto);
}
