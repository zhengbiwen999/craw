package com.springboot.common;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;


@Data
public abstract class AbstractSignRequest {
    /**
     * unix时间戳(当前时间的秒数)（eg:1410921659）
     */
    @Min(value = 1)
    private long timestamp;

    /**
     * 授权账号（由服务层分配,比如:1000）
     */
    @NotNull(message = "appId不能为空！")
    @Length(min = 1)
    private String appId;

    /**
     * 语言(eg:zh-CN)
     */
    @NotNull(message = "language不能为空！")
    @Length(min = 1)
    private String language;

    /**
     * 时区(eg:GMT+8)
     */
    @NotNull(message = "timeZone不能为空！")
    @Length(min = 1)
    private String timeZone;

    /**
     * 请求的唯一Id(用于调用链跟踪)
     */
    @NotNull(message = "requestId不能为空！")
    @Length(min = 6)
    private String requestId;

    /**
     * 安全校验值 (参考签名方法)
     */
    @NotNull(message = "sign不能为空！")
    @Length(min = 16)
    private String sign;

    public SignRequest toSignRequest() throws InvocationTargetException, IllegalAccessException {
        SignRequest signRequest = new SignRequest();
        signRequest.setAppId(this.getAppId());
        signRequest.setTimestamp(this.getTimestamp());
        signRequest.setLanguage(this.getLanguage());
        signRequest.setSign(this.getSign());
        signRequest.setRequestId(this.getRequestId());
        signRequest.setTimeZone(this.getTimeZone());
        return signRequest;
    }
}