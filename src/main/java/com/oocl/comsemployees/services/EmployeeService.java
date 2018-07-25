package com.oocl.comsemployees.services;

import com.oocl.comsemployees.Database;
import com.oocl.comsemployees.beans.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeService {

    public List<Employee> getAllEmployees() {
        return Database.getAllEmployees();
    }

    public boolean postEmployee(Employee employee) {
        return Database.getAllEmployees().add(employee);
    }

    public boolean deleteEmployee(Employee employee) {
        return Database.getAllEmployees().remove(employee);
    }
}
