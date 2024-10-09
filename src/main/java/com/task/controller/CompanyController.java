package com.task.controller;

import com.task.dto.CompanyDto;
import com.task.dto.CreateCompanyDto;
import com.task.service.CompanyService;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/company")
@Validated
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping(value="/get/{id}")
    public CompanyDto getCompanyData(@Pattern(regexp="[\\d]", message = "invalid entity ID") @PathVariable("id") String id) {
        return companyService.getCompanyByID(Long.valueOf(id));
    }

    @PostMapping(value="/create")
    public ResponseEntity createUserData(@RequestBody CreateCompanyDto company) {
        companyService.createCompany(company);
        return new ResponseEntity(Map.of("success", true), HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity deleteCompany(@Pattern(regexp="[\\d]", message = "invalid entity ID") @PathVariable("id") String id) {
        companyService.deleteCompany(Long.valueOf(id));
        return new ResponseEntity(Map.of("success", true), HttpStatus.OK);
    }

    @PutMapping(value="/update")
    public ResponseEntity updateCompany(@RequestBody CompanyDto companyDto) {
        companyService.updateCompany(companyDto);
        return new ResponseEntity(Map.of("success", true), HttpStatus.OK);
    }
}