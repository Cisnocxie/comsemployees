package com.oocl.comsemployees.repositories;

import com.oocl.comsemployees.beans.Company;
import com.oocl.comsemployees.beans.Employee;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @After
    public void tearDown() throws Exception {
        testEntityManager.clear();
    }

    @Test
    public void findAll() {
        //given
        testEntityManager.persist(new Company("abc", 20, new ArrayList<>()));
        testEntityManager.persist(new Company("xyz", 50, new ArrayList<>()));

        //when
        List<Company> companies = companyRepository.findAll();

        //then
        assertThat(companies.get(0).getCompanyName(), is("abc"));
        assertThat(companies.get(1).getCompanyName(), is("xyz"));
    }

    @Test
    public void findById() {
        //given
        testEntityManager.persist(new Company("abc", 20, new ArrayList<>()));

        //when
        Company company = companyRepository.findById(new Long(1)).orElse(null);

        //then
        assertThat(company.getCompanyName(), is("abc"));
    }

    @Test
    public void save() {
        //given,when
        Company company = companyRepository.save(new Company("abc", 20, new ArrayList<>()));

        //then
        assertThat(company.getCompanyName(), is("abc"));
    }

    @Test
    public void deleteById() {
        //given
        testEntityManager.persist(new Company("abc", 20, new ArrayList<>()));

        //when
        companyRepository.deleteById(new Long(1));

        //then
        assertThat(companyRepository.findAll().size(), is(0));
    }

    @Test
    public void getPageWithSize() {
        //given
        testEntityManager.persist(new Company("abc", 20, new ArrayList<>()));
        testEntityManager.persist(new Company("xyz", 50, new ArrayList<>()));
        testEntityManager.persist(new Company("ijk", 50, new ArrayList<>()));

        //when
        List<Company> companies = companyRepository.findAll(PageRequest.of(1, 2)).getContent();

        //then
        assertThat(companies.get(0).getCompanyName(), is("ijk"));
    }
}
