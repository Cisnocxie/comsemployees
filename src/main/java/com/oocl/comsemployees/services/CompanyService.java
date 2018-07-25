package com.oocl.comsemployees.services;

import com.oocl.comsemployees.Database;
import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CompanyService {
    public List<Company> getAllCompanies() {
        return Database.getAllCompanies();
    }

    public Company getCompany(int id) {
        return Database.getCompany(id);
    }

    public boolean postCompany(Company company) {
        return Database.getAllCompanies().add(company);
    }

    public boolean putCompany(int companyId, Company company) {
        boolean isSuccess = false;
        Optional optional = Database.getAllCompanies().stream().filter(company1 -> company1.getId() == companyId).findFirst();
        if (optional.isPresent()) {
            Database.getAllCompanies().set(Database.getAllCompanies().indexOf(optional.get()), company);
            isSuccess = true;
        }
        return isSuccess;
    }

    public boolean deleteCompany(int companyId) {
        Company company = Database.getAllCompanies().stream().filter(company1 -> company1.getId() == companyId).findFirst().get();
        company.getEmployees().forEach(employee -> Database.getAllEmployees().remove(employee));
        return Database.getAllCompanies().remove(company);
    }
}
