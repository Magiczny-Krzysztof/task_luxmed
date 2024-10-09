package com.task.converter;

import com.task.dto.CompanyDto;
import com.task.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyDto2CompanyMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Company mapToUpdateCompany(CompanyDto company);

}
