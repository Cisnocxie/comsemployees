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
    public Map<String,String> postCompany(@RequestBody Company company) {
        Map<String, String> map = new HashMap<>();
        map.put("issuccess", companyService.postCompany(company) ? "true" : "false");
        return map;
    }

    @PutMapping("/companies/{companyId}")
    public Map<String, String> putCompany(@PathVariable int companyId, @RequestBody Company company) {
        Map<String, String> map = new HashMap<>();
        map.put("issuccess", companyService.putCompany(companyId, company) ? "true" : "false");
        return map;
    }

    @DeleteMapping("/companies/{companyId}")
    public Map<String, String> deleteCompany(@PathVariable int companyId) {
        Map<String, String> map = new HashMap<>();

        map.put("issuccess", companyService.deleteCompany(companyId) ? "true" : "false");
        return map;
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/companies/{id}/employees")
    public List<Employee> getEmployeesOfCompany(@PathVariable int id) {
        return companyService.getCompany(id).getEmployees();
    }

    @GetMapping("/companies/page/{page}/pageSize/{size}")
    public List<Company> getCompaniesInPage(@PathVariable int page, @PathVariable int size) {
        return companyService.getcompaniesInPage(page, size);
    }
}
