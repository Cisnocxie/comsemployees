package com.oocl.comsemployees.controllers;

import com.oocl.comsemployees.Database;
import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import com.oocl.comsemployees.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping("/companies")
    public Company postCompany(@RequestBody Company company) {
        return companyService.postCompany(company);
    }

    @PutMapping("/companies/{companyId}")
    public Company putCompany(@PathVariable long companyId, @RequestBody Company company) {
        return companyService.putCompany(companyId, company);
    }

    @DeleteMapping("/companies/{companyId}")
    public void deleteCompany(@PathVariable long companyId) {
        companyService.deleteCompany(companyId);
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable long id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/companies/{id}/employees")
    public List<Employee> getEmployeesOfCompany(@PathVariable long id) {
        return companyService.getCompany(id).getEmployees();
    }

    @GetMapping("/companies/page/{page}/pageSize/{size}")
    public List<Company> getCompaniesInPage(@PathVariable int page, @PathVariable int size) {
        return companyService.getcompaniesInPage(page, size);
    }
}
