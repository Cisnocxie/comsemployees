package com.oocl.comsemployees;

import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static List<Company> companies = new ArrayList<>();

    private static List<Employee> employees = new ArrayList<>();

    public static List<Company> getAllCompanies() {
        return companies;
    }

    public static Company getCompany(int id) {
        return companies.stream().filter(company -> company.getId() == id).findFirst().orElse(null);
    }

    public static List<Employee> getAllEmployees() {
        return employees;
    }

    public static Employee getEmployee(int id) {
        return employees.stream().filter(employee -> employee.getId() == id) .findFirst().orElse(null);
    }
}
