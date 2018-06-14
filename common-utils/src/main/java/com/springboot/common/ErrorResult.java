package com.springboot.common;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResult {

    public ErrorResult(){}

    /**
     * 错误代码
     */
    private String errNo;

    /**
     * 错误消息
     */
    private String errMsg;

    /**
     * 异常详情
     */
    private String errException;

    public String getErrNo() {
        return errNo;
    }

    public ErrorResult setErrNo(String errNo) {
        this.errNo = errNo;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public ErrorResult setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public String getErrException() {
        return errException;
    }

    public ErrorResult setErrException(String errException) {
        this.errException = errException;
        return this;
    }
}