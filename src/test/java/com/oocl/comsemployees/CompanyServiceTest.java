package com.oocl.comsemployees;

import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import com.oocl.comsemployees.services.CompanyService;
import org.junit.Test;
import org.mockito.Mockito;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CompanyServiceTest {

    private CompanyService companyService = new CompanyService();

    @Test
    public void should_get_companies_when_call_getAllCompanies() {
        CompanyService companyService = Mockito.mock(CompanyService.class);

        Mockito.verify(companyService).getAllCompanies();
    }

    @Test
    public void should_get_true_when_call_postCompany_given_a_company() {
        Company company = new Company("bbc", 50, new ArrayList<>());

        assertThat(companyService.postCompany(company), is(true));
    }

    @Test
    public void should_get_true_when_call_putCompany_given_a_exist_id() {
        Company company = new Company("bbc", 50, new ArrayList<>());
        companyService.postCompany(company);

        assertThat(companyService.putCompany(company.getId(), new Company("aaq", 50, new ArrayList<>())), is(true));
    }

    @Test
    public void should_get_false_when_call_putCompany_given_a_unexist_id() {
        assertThat(companyService.putCompany(123, new Company("aaq", 50, new ArrayList<>())), is(false));
    }
}
