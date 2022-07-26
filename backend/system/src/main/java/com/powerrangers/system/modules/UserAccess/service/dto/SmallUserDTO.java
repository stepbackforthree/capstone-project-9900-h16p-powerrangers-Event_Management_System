package com.powerrangers.system.modules.UserAccess.service.dto;

import com.powerrangers.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("User login and sign up request object")
public class SmallUserDTO extends BaseDTO implements Serializable {

    @ApiModelProperty("userName")
    private String userName;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("password")
    private String password;

    @ApiModelProperty("role")
    private String role;

    @ApiModelProperty("Whether to receive email from host")
    private Boolean isReceived;
}
