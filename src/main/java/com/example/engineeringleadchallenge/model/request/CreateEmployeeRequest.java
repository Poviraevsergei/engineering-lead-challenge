package com.example.engineeringleadchallenge.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateEmployeeRequest {

    @ApiModelProperty(required = true, dataType = "string", notes = "employee's firstname")
    private String firstName;

    @ApiModelProperty(required = true, dataType = "string", notes = "employee's lastname")
    private String lastname;

    @ApiModelProperty(required = true, dataType = "integer", notes = "employee's age")
    private Integer age;

    @ApiModelProperty(required = true, dataType = "string", notes = "employee's email")
    private String email;
}