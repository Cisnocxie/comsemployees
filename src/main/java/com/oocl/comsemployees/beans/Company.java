package com.oocl.comsemployees.beans;

import java.util.List;

public class Company {
    private int id;
    private String name;
    private int employeesNumber;
    private List<Employee> employees;

    public Company(String name, int employeesNumber, List<Employee> employees) {
        this.id = this.hashCode();
        this.name = name;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return name;
    }

    public void setCompanyName(String companyName) {
        this.name = companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
