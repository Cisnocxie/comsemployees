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
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/employees")
    public Map<String, String> postEmployee(@RequestBody Employee employee) {
        Map<String, String> map = new HashMap<>();
        map.put("success", employeeService.postEmployee(employee) ? "true" : "false");
        return map;
    }

    @PutMapping("/employees/{id}")
    public Map<String, String> putEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Map<String, String> map = new HashMap<>();
        map.put("issuccess", employeeService.putEmployee(id, employee) ? "true" : "false");
        return map;
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, String> deleteEmployee(@PathVariable int id) {
        Map<String, String> map = new HashMap<>();

        map.put("issuccess", employeeService.deleteEmployee(id) ? "true" : "false");
        return map;
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
