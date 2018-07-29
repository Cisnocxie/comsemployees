package com.oocl.comsemployees.services;

import com.oocl.comsemployees.Database;
import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import com.oocl.comsemployees.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CompanyService {

    @Autowired
    private CompanyRepository componyRepository;

    public List<Company> getAllCompanies() {
        return componyRepository.findAll();
    }

    public Company getCompany(long id) {
        return componyRepository.findById(id).get();
    }

    public Company postCompany(Company company) {
        return componyRepository.save(company);
    }

    public Company putCompany(long companyId, Company company) {
        Company company1 = componyRepository.findById(companyId).get();
        company1.setEmployees(company.getEmployees());
        company1.setEmployeesNumber(company.getEmployeesNumber());
        company1.setCompanyName(company.getCompanyName());
        return componyRepository.save(company1);
    }

    public void deleteCompany(long companyId) {
        componyRepository.deleteById(companyId);
    }

    public List<Company> getcompaniesInPage(int page, int size) {
        return componyRepository.findAll(PageRequest.of(page - 1, size)).getContent();
    }
}
