package com.task.converter;

import com.task.dto.CompanyDto;
import com.task.dto.CreateCompanyDto;
import com.task.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateCompanyDto2CompanyMapper {

    @Mapping(source = "name", target = "name")
    Company mapToCreateCompany(CreateCompanyDto company);

}
