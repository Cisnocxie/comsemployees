package com.oocl.comsemployees.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import com.oocl.comsemployees.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private EmployeeService service;

    @Test
    public void postEmployee_ReturnOk() throws Exception{
        Employee employee = new Employee("xiaoming", 21, "male", 8000);
        given(service.postEmployee(any(Employee.class))).willReturn(employee);

        ResultActions ressultAction = mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee)));

        ressultAction.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getAllEmployees() throws Exception{
        //given
        Employee employee1 = new Employee("xiaoming", 21, "male", 8000);
        Employee employee2 = new Employee("xiaohong", 22, "female", 8000);
        List<Employee> employees = new ArrayList<>(Arrays.asList(employee1, employee2));
        given(service.getAllEmployees()).willReturn(employees);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees"));

        resultActions
                .andExpect(jsonPath("$[0].name", is("xiaoming")))
                .andExpect(jsonPath("$[1].name", is("xiaohong")));
    }

    @Test
    public void getCompanyById() throws Exception {
        //given
        Employee employee1 = new Employee("xiaoming", 21, "male", 8000);
        given(service.getEmployee(anyLong())).willReturn(employee1);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/{0}", 1L));

        //then
        resultActions
                .andExpect(jsonPath("$.name", is("xiaoming")));
    }

    @Test
    public void putCompany() throws Exception {
        //given
        Employee employee1 = new Employee("xiaoming", 21, "male", 8000);
        Employee employee2 = new Employee("xiaohong", 22, "female", 8000);
        given(service.putEmployee(anyLong(), any(Employee.class))).willReturn(employee2);

        //when
        ResultActions resultActions = mockMvc.perform(put("/employees/{0}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee2)));

        //then
        resultActions
                .andExpect(jsonPath("$.name", is("xiaohong")))
                .andExpect(jsonPath("$.age", is(22)));
    }

    @Test
    public void deleteCompany() throws Exception {
        //given
        Employee employee1 = new Employee("xiaoming", 21, "male", 8000);

        //when, then
        mockMvc.perform(delete("/employees/{0}", 1L));
    }

    @Test
    public void getMaleEmployees() throws Exception {
        //given
        Employee employee1 = new Employee("xiaoming", 21, "male", 8000);
        Employee employee2 = new Employee("xiaohong", 22, "female", 8000);
        List<Employee> employees = new ArrayList<>(Collections.singletonList(employee1));
        given(service.getMaleEmployees()).willReturn(employees);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/male"));

        //then
        resultActions
                .andExpect(jsonPath("$[0].name", is("xiaoming")));
    }

    @Test
    public void getCompaniesInPage() throws Exception {
        //given
        Employee employee1 = new Employee("xiaoming", 21, "male", 8000);
        Employee employee2 = new Employee("xiaohong", 22, "female", 8000);
        Employee employee3 = new Employee("xiaogang", 20, "male", 7000);
        given(service.getEmployeesInPage(anyInt(), anyInt())).willReturn(new ArrayList<>(Collections.singletonList(employee3)));

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/page/{0}/pageSize/{1}", 2, 2))
                .andExpect(jsonPath("$[0].name", is("xiaogang")));
    }
}
