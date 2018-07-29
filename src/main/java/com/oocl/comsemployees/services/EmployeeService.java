package com.oocl.comsemployees.services;

import com.oocl.comsemployees.Database;
import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import com.oocl.comsemployees.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(long id) {
        return employeeRepository.findById(id).get();
    }

    public Employee postEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee putEmployee(long employeeId, Employee employee) {
        Employee employee1 = employeeRepository.findById(employeeId).get();
        employee1.setName(employee.getName());
        employee1.setAge(employee.getAge());
        employee1.setGender(employee.getGender());
        employee1.setSalary(employee.getSalary());
        return employeeRepository.save(employee1);
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getMaleEmployees() {
        return employeeRepository.findByGender("male");
    }

    public List<Employee> getEmployeesInPage(int page, int size) {
        return employeeRepository.findAll(new PageRequest(page, size)).getContent();
    }
}
