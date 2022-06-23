package com.powerrangers.system.modules.userAccess.domain;

import lombok.Data;

@Data
public class Host extends User {
    private Integer hostId;

    private Boolean isAuthorized;

    private Byte[] qualification;

    private Boolean isVerified;
}
