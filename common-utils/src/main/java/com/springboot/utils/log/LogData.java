package com.springboot.utils.log;

import lombok.Data;

import java.util.List;


@Data
public class LogData {

    /**
     * 调用结果
     */
    private String result;

    /**
     * 服务器执行耗时
     */
    private long elapsedMilliseconds;

    /**
     * 调用方完整信息
     */
    private String invocation;

    /**
     * Rest服务参数校验失败的错误信息
     */
    private List<String> paramCheckErrors;

    /**
     * 服务调用发生异常时，完整的异常信息
     */
    private String errorStack;

    /**
     * Rest服务的请求url
     */
    private String restUrl;

    /**
     * Rest服务的QueryString请求参数
     */
    private String queryString;

    /**
     * 调用方的IP
     */
    private String remoteIp;

    /**
     * 主机IP
     */
    private String hostIp;


    /**
     * traceId
     */
    private String requestId;

}
