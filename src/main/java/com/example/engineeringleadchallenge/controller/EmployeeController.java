package com.example.engineeringleadchallenge.controller;

import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.engineeringleadchallenge.model.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.engineeringleadchallenge.service.EmployeeService;
import com.example.engineeringleadchallenge.model.request.CreateEmployeeRequest;
import com.example.engineeringleadchallenge.model.request.UpdateEmployeeStageRequest;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/employee", produces = "application/json")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    @ApiOperation(value = "Create employee")
    @ApiResponse(code = 201, message = "Successful created employee")
    ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        Employee employee = employeeService.createEmployee(createEmployeeRequest);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/changeStage")
    @ApiOperation(value = "Change state")
    @ApiResponses({
            @ApiResponse(code = 200, message = "State was changed successfully"),
            @ApiResponse(code = 204, message = "State was changed unsuccessfully")
    })
    ResponseEntity<HttpStatus> changeTheStateOfEmployee(@RequestBody UpdateEmployeeStageRequest updateEmployeeStageRequest) throws Exception {
        if (employeeService.changeTheStateOfEmployee(updateEmployeeStageRequest)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}