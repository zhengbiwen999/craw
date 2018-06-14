package com.springboot.common;

import lombok.Data;


@Data
public class SignRequest {
    private long timestamp;
    private String appId;
    private String language;
    private String timeZone;
    private String requestId;
    private String sign;
}

