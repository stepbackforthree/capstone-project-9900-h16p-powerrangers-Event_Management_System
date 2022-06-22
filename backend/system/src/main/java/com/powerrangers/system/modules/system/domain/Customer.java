package com.powerrangers.system.modules.system.domain;

import lombok.Data;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@Data
public class Customer extends User {
    private Integer customerId;

    private Boolean isVerified;

    private JSONObject prefTag;
}
