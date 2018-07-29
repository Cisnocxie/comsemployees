package com.oocl.comsemployees.controllers;

import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import com.oocl.comsemployees.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable long id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee) {
        return employeeService.postEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee putEmployee(@PathVariable long id, @RequestBody Employee employee) {
        return employeeService.putEmployee(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/male")
    public List<Employee> getMaleEmployees() {
        return employeeService.getMaleEmployees();
    }

    @GetMapping("/employees/page/{page}/pageSize/{size}")
    public List<Employee> getEmployeesInPage(@PathVariable int page, @PathVariable int size) {
        return employeeService.getEmployeesInPage(page, size);
    }
}
