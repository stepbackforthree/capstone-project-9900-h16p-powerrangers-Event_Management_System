package com.powerrangers.common.base;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseEntity {
    private String create_by;

    private String update_by;

    private Timestamp create_time;

    private Timestamp update_time;
}
