package com.oocl.comsemployees;

import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import com.oocl.comsemployees.services.CompanyService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.IsSame.;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CompanyServiceTest {

//    @Test
//    public void should_get_companies_when_call_getAllCompanies() {
//        CompanyService companyService = Mockito.mock(CompanyService.class);
//
//        Mockito.verify(companyService).getAllCompanies();
//    }

//    @Test
//    public void should_get_company_id1_when_call_getCompany_given_1() {
//        CompanyService companyService = new CompanyService();
//
////        assertEquals(companyService.getCompany(1),new Company(1, "aaa", 25, new ArrayList<Employee>(
////                Arrays.asList(new Employee(1, "a1", 21, "male", 6000)
////                ))));
//        assertThat(companyService.getCompany(1), isSame(new Company(1, "aaa", 25, new ArrayList<Employee>(
//                Arrays.asList(new Employee(1, "a1", 21, "male", 6000)
//        )))));
//    }
}
