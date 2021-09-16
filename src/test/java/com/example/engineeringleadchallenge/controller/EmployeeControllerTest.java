package com.example.engineeringleadchallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.example.engineeringleadchallenge.util.Stages;
import com.example.engineeringleadchallenge.model.Employee;
import com.example.engineeringleadchallenge.service.EmployeeService;
import com.example.engineeringleadchallenge.model.request.CreateEmployeeRequest;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
class EmployeeControllerTest {

    MockMvc mvc;

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    private final Employee employee = new Employee();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        employee.setId(5L);
        employee.setEmail("test-email@gmail.com");
        employee.setStatus(Stages.ADDED);
        employee.setAge(30);
        employee.setFirstName("Didier");
        employee.setLastName("Drogba");
    }

    @Test
    void createEmployee() throws Exception {
        when(employeeService.createEmployee(any(CreateEmployeeRequest.class))).thenReturn(employee);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new CreateEmployeeRequest());

        MvcResult result = mvc.perform(post("/employee/create").contentType(APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(employeeService, times(1)).createEmployee(any());
    }

    @Test
    void changeTheStateOfEmployee() throws Exception {
        when(employeeService.changeTheStateOfEmployee(any())).thenReturn(true);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new Employee());

        MvcResult result = mvc.perform(put("/employee/changeStage").contentType(APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(employeeService, times(1)).changeTheStateOfEmployee(any());
    }
}