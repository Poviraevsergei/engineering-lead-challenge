package com.example.engineeringleadchallenge.service;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.engineeringleadchallenge.util.Stages;
import com.example.engineeringleadchallenge.model.Employee;
import com.example.engineeringleadchallenge.repository.EmployeeRepository;
import com.example.engineeringleadchallenge.service.impl.EmployeeServiceImpl;
import com.example.engineeringleadchallenge.model.request.CreateEmployeeRequest;
import com.example.engineeringleadchallenge.model.request.UpdateEmployeeStageRequest;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private final Employee employee = new Employee();
    private final UpdateEmployeeStageRequest updateEmployeeStageRequest = new UpdateEmployeeStageRequest();

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        employee.setId(5L);
        employee.setEmail("test-email@gmail.com");
        employee.setStatus(Stages.ADDED);
        employee.setAge(30);
        employee.setFirstName("Didier");
        employee.setLastName("Drogba");
        updateEmployeeStageRequest.setId(5L);
        updateEmployeeStageRequest.setStage("ACTIVE");
    }

    @Test
    void createEmployee() {
        when(employeeRepository.save(any())).thenReturn(employee);

        assertThat(employeeService.createEmployee(new CreateEmployeeRequest()), equalTo(employee));
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    void changeTheStateOfEmployee() {
        when(employeeRepository.getEmployeeById(anyLong())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any())).thenReturn(employee);

        Boolean result = employeeService.changeTheStateOfEmployee(updateEmployeeStageRequest);

        assertThat(result, equalTo(true));
        verify(employeeRepository, times(1)).getEmployeeById(anyLong());
        verify(employeeRepository, times(1)).save(any());
    }
}