package com.oocl.comsemployees.services;

import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CompanyService {
    private List<Company> companies = new ArrayList<>(Arrays.asList(
            new Company("aaa", 25, new ArrayList<Employee>(
                    Arrays.asList(new Employee("a1", 21, "male", 6000)
            )))
    ));

    public List<Company> getAllCompanies() {
        return companies;
    }

    public Company getCompany(int id) {
        return companies.stream().filter(company -> company.getId() == id).findFirst().get();
    }

    public boolean postCompany(Company company) {
        return companies.add(company);
    }

    public boolean putCompany(int companyId, Company company) {
        boolean isSuccess = false;
        Optional optional = companies.stream().filter(company1 -> company1.getId() == companyId).findFirst();
        if (optional.isPresent()) {
            companies.set(companies.indexOf(optional.get()), company);
            isSuccess = true;
        }
        return isSuccess;
    }
}
