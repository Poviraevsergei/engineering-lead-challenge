package com.example.engineeringleadchallenge.service;

import com.example.engineeringleadchallenge.model.Employee;
import com.example.engineeringleadchallenge.model.request.CreateEmployeeRequest;
import com.example.engineeringleadchallenge.model.request.UpdateEmployeeStageRequest;

public interface EmployeeService {
    Employee createEmployee(CreateEmployeeRequest createEmployeeRequest);

    Boolean changeTheStateOfEmployee(UpdateEmployeeStageRequest updateEmployeeStageRequest) throws Exception;
}
