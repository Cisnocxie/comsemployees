package com.oocl.comsemployees.services;

import com.oocl.comsemployees.Database;
import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    public List<Employee> getAllEmployees() {
        return Database.getAllEmployees();
    }

    public Employee getEmployee(int id) {
        return Database.getEmployee(id);
    }

    public boolean postEmployee(Employee employee) {
        return Database.getAllEmployees().add(employee);
    }

    public boolean putEmployee(int employeeId, Employee employee) {
        boolean isSuccess = false;
        Optional optional = Database.getAllEmployees().stream().filter(employee1 -> employee1.getId() == employeeId).findFirst();
        if (optional.isPresent()) {
            Employee employee1 = (Employee)optional.get();
            employee1.setName(employee.getName());
            employee1.setAge(employee.getAge());
            employee1.setGender(employee.getGender());
            employee1.setSalary(employee.getSalary());
            isSuccess = true;
        }
        return isSuccess;
    }

    public boolean deleteEmployee(int id) {
        Employee employee = Database.getAllEmployees()
                .stream()
                .filter(employee1 -> employee1.getId() == id)
                .findFirst()
                .get();
        return Database.getAllEmployees().remove(employee);
    }

    public List<Employee> getMaleEmployees() {
        return Database.getAllEmployees()
                .stream()
                .filter(employee -> employee.getGender().equals("male"))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesInPage(int page, int size) {
        int listsize = Database.getAllEmployees().size();
        int start, end;
        if ((page - 1) * size < listsize) {
            start = (page - 1) * size;
        } else {
            return null;
        }
        end = page * size > listsize ? listsize : page * size;
        return Database.getAllEmployees().subList(start, end);
    }
}
