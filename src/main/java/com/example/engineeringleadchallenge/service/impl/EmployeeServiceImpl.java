package com.example.engineeringleadchallenge.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.engineeringleadchallenge.util.Stages;
import com.example.engineeringleadchallenge.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.engineeringleadchallenge.service.EmployeeService;
import com.example.engineeringleadchallenge.repository.EmployeeRepository;
import com.example.engineeringleadchallenge.model.request.CreateEmployeeRequest;
import com.example.engineeringleadchallenge.model.request.UpdateEmployeeStageRequest;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(CreateEmployeeRequest createEmployeeRequest) {
        Employee employee = new Employee();
        employee.setFirstName(createEmployeeRequest.getFirstName());
        employee.setLastName(createEmployeeRequest.getLastname());
        employee.setAge(createEmployeeRequest.getAge());
        employee.setEmail(createEmployeeRequest.getEmail());
        employee.setCreated(LocalDate.now());
        employee.setChanged(LocalDate.now());
        employee.setStatus(Stages.ADDED);
        return employeeRepository.save(employee);
    }

    @Override
    public Boolean changeTheStateOfEmployee(UpdateEmployeeStageRequest updateEmployeeStageRequest) {
        try {
            Employee employee = employeeRepository.getEmployeeById(updateEmployeeStageRequest.getId()).orElseThrow(() -> new Exception("Employee not found."));
            employee.setStatus(Stages.valueOf(updateEmployeeStageRequest.getStage().trim().toUpperCase()));
            employee.setChanged(LocalDate.now());
            employeeRepository.save(employee);
            return true;
        } catch (Exception exception) {
            System.out.printf("Employee id=%d stage don't update", updateEmployeeStageRequest.getId());
            return false;
        }
    }
}