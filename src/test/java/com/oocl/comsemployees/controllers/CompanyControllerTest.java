package com.oocl.comsemployees.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.comsemployees.beans.Company;
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
    public


}
