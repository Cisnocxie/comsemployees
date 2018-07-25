package com.oocl.comsemployees.controllers;

import com.oocl.comsemployees.Database;
import com.oocl.comsemployees.beans.Company;
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
}
