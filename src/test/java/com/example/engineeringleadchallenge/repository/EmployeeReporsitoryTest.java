package com.example.engineeringleadchallenge.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.BeforeEach;
import com.example.engineeringleadchallenge.util.Stages;
import com.example.engineeringleadchallenge.model.Employee;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class EmployeeReporsitoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final Employee employee = new Employee();

    @BeforeEach
    void setup() {
        employee.setId(5L);
        employee.setEmail("test-email@gmail.com");
        employee.setStatus(Stages.ADDED);
        employee.setAge(30);
        employee.setFirstName("Didier");
        employee.setLastName("Drogba");
    }

    @Test
    public void getEmployeeById() {
        Employee savedEmployee = employeeRepository.save(employee);
        Employee findEmployee = employeeRepository.findById(savedEmployee.getId()).orElseThrow();

        assertThat(savedEmployee, is(notNullValue()));
        assertThat(savedEmployee.getId(), is(findEmployee.getId()));
    }

    @Test
    public void save() {
        List<Employee> listEmployees = (List<Employee>) employeeRepository.findAll();
        employeeRepository.save(employee);
        List<Employee> newListEmplyees = (List<Employee>) employeeRepository.findAll();

        assertThat(newListEmplyees.size(), allOf(notNullValue(), equalTo(listEmployees.size() + 1)));
    }
}