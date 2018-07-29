package com.oocl.comsemployees.repositories;

import com.oocl.comsemployees.beans.Employee;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @After
    public void tearDown() throws Exception {
        testEntityManager.clear();
    }

    @Test
    public void findByGender() {
        //given
        testEntityManager.persist(new Employee("xiaoming", 21, "male", 8000));
        testEntityManager.persist(new Employee("xiaohong", 22, "female", 8000));

        //when
        List<Employee> employees = employeeRepository.findByGender("male");

        //then
        assertThat(employees.get(0).getName(), is("xiaoming"));
    }

    @Test
    public void findAll() {
        //given
        testEntityManager.persist(new Employee("xiaoming", 21, "male", 8000));
        testEntityManager.persist(new Employee("xiaohong", 22, "female", 8000));

        //when
        List<Employee> employees = employeeRepository.findAll();

        //then
        assertThat(employees.get(0).getName(), is("xiaoming"));
        assertThat(employees.get(1).getName(), is("xiaohong"));
    }

    @Test
    public void findById() {
        //given
        testEntityManager.persist(new Employee("xiaoming", 21, "male", 8000));

        //when
        Employee employee = employeeRepository.findById(new Long(1)).orElse(null);

        //then
        assertThat(employee.getName(), is("xiaoming"));
    }

    @Test
    public void save() {
        //given,when
        Employee employee = employeeRepository.save(new Employee("xiaoming", 21, "male", 8000));

        //then
        assertThat(employee.getName(), is("xiaoming"));
    }

    @Test
    public void deleteById() {
        //given
        testEntityManager.persist(new Employee("xiaoming", 21, "male", 8000));

        //when
        employeeRepository.deleteById(new Long(1));

        //then
        assertThat(employeeRepository.findAll().size(), is(0));
    }

    @Test
    public void getPageWithSize() {
        //given
        testEntityManager.persist(new Employee("xiaoming", 21, "male", 8000));
        testEntityManager.persist(new Employee("xiaohong", 22, "female", 8000));
        testEntityManager.persist(new Employee("xiaogang", 20, "male", 7000));

        //when
        List<Employee> employees = employeeRepository.findAll(PageRequest.of(1, 2)).getContent();

        //then
        assertThat(employees.get(0).getName(), is("xiaogang"));
    }
}
