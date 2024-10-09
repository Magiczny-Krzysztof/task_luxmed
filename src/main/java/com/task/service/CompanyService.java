package com.task.service;

import com.task.converter.Company2CompanyDtoMapper;
import com.task.converter.CompanyDto2CompanyMapper;
import com.task.converter.CreateCompanyDto2CompanyMapper;
import com.task.dto.CompanyDto;
import com.task.dto.CreateCompanyDto;
import com.task.entity.Company;
import com.task.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final Company2CompanyDtoMapper company2CompanyDtoMapper;
    private final CompanyDto2CompanyMapper companydto2CompanyMapper;
    private final CreateCompanyDto2CompanyMapper createCompanyDto2CompanyMapper;

    @Transactional(readOnly = true)
    public CompanyDto getCompanyByID(Long companyID) {

        Optional.ofNullable(companyRepository.getCompanyByID(companyID))
                .orElseThrow(() -> new RuntimeException("Entity don't exists"));

        return company2CompanyDtoMapper.
                mapToCompanyDto(companyRepository.getCompanyByID(companyID));
    }

    public void createCompany(CreateCompanyDto company) {
        Optional.ofNullable(company.getName())
                        .orElseThrow(() -> new RuntimeException("Invalid entity data"));
        companyRepository.save(createCompanyDto2CompanyMapper.mapToCreateCompany(company));
    }

    public void updateCompany(CompanyDto companyDto) {
        if (companyDto.getId() == null || companyDto.getName() == null) {
            throw new RuntimeException("Invalid entity data");
        }

        Optional.ofNullable(companyRepository.getCompanyByID(companyDto.getId()))
                        .orElseThrow(() -> new RuntimeException("Entity don't exists"));

        companyRepository.save(companydto2CompanyMapper.mapToUpdateCompany(companyDto));
    }

    public void deleteCompany(Long id) {

        Optional.ofNullable(companyRepository.getCompanyByID(id))
                .orElseThrow(() -> new RuntimeException("Entity don't exists"));

        companyRepository.deleteById(id);
    }
}
