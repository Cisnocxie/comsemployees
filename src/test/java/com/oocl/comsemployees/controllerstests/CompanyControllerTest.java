package com.oocl.comsemployees.controllerstests;

import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.controllers.CompanyController;
import com.oocl.comsemployees.services.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService service;

    @Test
    public void postCompany_ReturnSuccess() throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("issuccess", "true");
        given(service.postCompany(any(Company.class))).willReturn(true);

        mockMvc.perform(post("/companies"))
                .andExpect(jsonPath("issuccess").value("true"));
    }
}
