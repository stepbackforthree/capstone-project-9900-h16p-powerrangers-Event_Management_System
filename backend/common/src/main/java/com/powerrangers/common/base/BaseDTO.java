package com.powerrangers.common.base;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class BaseDTO implements Serializable {

    private String createBy;

    private String updateBy;

    private Timestamp createTime;

    private Timestamp updateTime;
}
