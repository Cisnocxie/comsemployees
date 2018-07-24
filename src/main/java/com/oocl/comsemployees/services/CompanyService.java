package com.oocl.comsemployees.services;

import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CompanyService {
    private List<Company> companies = new ArrayList<>(Arrays.asList(
            new Company(1, "aaa", 25, new ArrayList<Employee>(
                    Arrays.asList(new Employee(1, "a1", 21, "male", 6000)
            )))
    ));

    public List<Company> getAllCompanies() {
        return companies;
    }

    public Company getCompany(int id) {
        return companies.stream().filter(company -> company.getId() == id).findFirst().get();
    }
}
