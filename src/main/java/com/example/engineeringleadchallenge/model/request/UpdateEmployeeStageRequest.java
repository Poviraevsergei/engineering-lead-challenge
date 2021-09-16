package com.example.engineeringleadchallenge.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Employee updating model")
public class UpdateEmployeeStageRequest {
    @ApiModelProperty(required = true, dataType = "long", notes = "employee id")
    private Long id;

    private String stage;
}