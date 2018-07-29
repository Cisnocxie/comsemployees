package com.oocl.comsemployees.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import com.oocl.comsemployees.services.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CompanyService service;

    @Test
    public void postCompany_ReturnOk() throws Exception{
        Company company = new Company("abc", 20, new ArrayList<>());
        given(service.postCompany(any(Company.class))).willReturn(company);

        ResultActions ressultAction = mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(company)));

        ressultAction.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getAllComponies() throws Exception{
        //given
        Company company1 = new Company("abc", 20, new ArrayList<>());
        Company company2 = new Company("xyz", 50, new ArrayList<>());
        List<Company> companies = new ArrayList<>(Arrays.asList(company1, company2));
        given(service.getAllCompanies()).willReturn(companies);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies"));

        resultActions
                .andExpect(jsonPath("$[0].name", is("abc")))
                .andExpect(jsonPath("$[1].name", is("xyz")));
    }

    @Test
    public void getCompanyById() throws Exception {
        //given
        Company company1 = new Company("abc", 20, new ArrayList<>());
        given(service.getCompany(anyLong())).willReturn(company1);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/{0}", 1L));

        //then
        resultActions
                .andExpect(jsonPath("$.name", is("abc")));
    }

    @Test
    public void putCompany() throws Exception {
        //given
        Company company1 = new Company("abc", 20, new ArrayList<>());
        Company company2 = new Company("xyz", 50, new ArrayList<>());
        given(service.putCompany(anyLong(), any(Company.class))).willReturn(company2);

        //when
        ResultActions resultActions = mockMvc.perform(put("/companies/{0}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(company2)));

        //then
        resultActions
                .andExpect(jsonPath("$.name", is("xyz")))
                .andExpect(jsonPath("$.employeesNumber", is(50)));
    }

    @Test
    public void deleteCompany() throws Exception {
        //given
        Company company1 = new Company("abc", 20, new ArrayList<>());

        //when, then
        mockMvc.perform(delete("/companies/{0}", 1L));
    }

    @Test
    public void getCompanyEmployees() throws Exception {
        //given
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("xiaoming", 21, "male", 8000),
                new Employee("xiaohong", 22, "female", 8000)
        ));
        Company company1 = new Company("abc", 20, employees);
        given(service.getCompany(anyLong())).willReturn(company1);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/{0}/employees", 1L));

        //then
        resultActions
                .andExpect(jsonPath("$[0].name", is("xiaoming")))
                .andExpect(jsonPath("$[1].name", is("xiaohong")));
    }

    @Test
    public void getCompaniesInPage() throws Exception {
        //given
        Company company1 = new Company("abc", 20, new ArrayList<>());
        Company company2 = new Company("xyz", 50, new ArrayList<>());
        Company company3 = new Company("ijk", 50, new ArrayList<>());
        given(service.getcompaniesInPage(anyInt(), anyInt())).willReturn(new ArrayList<>(Collections.singletonList(company3)));

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/page/{0}/pageSize/{1}", 2, 2))
                .andExpect(jsonPath("$[0].name", is("ijk")))
                .andExpect(jsonPath("$[0].employeesNumber", is(50)));
    }
}
