package com.task.converter;


import com.task.dto.CompanyDto;
import com.task.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface Company2CompanyDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    CompanyDto mapToCompanyDto(Company company);
}
