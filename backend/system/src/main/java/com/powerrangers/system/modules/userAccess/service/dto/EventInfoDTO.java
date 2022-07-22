package com.powerrangers.system.modules.userAccess.service.dto;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class EventInfoDTO implements Serializable {
    String location;
    String siteDescription;
    Timestamp startTime;

}
