package com.oocl.comsemployees.beans;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int employeesNumber;

    @OneToMany
    private List<Employee> employees;

    public Company(String name, int employeesNumber, List<Employee> employees) {
        this.name = name;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

    public Company(){}

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
