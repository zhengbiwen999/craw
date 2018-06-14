package com.springboot.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by yangjunming on 2016/12/27.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class DataResult<T extends Response> implements Serializable {

    private static final long serialVersionUID = -5243430169201441703L;

    /**
     * (业务层)返回的数据
     */
    private T data;

    /**
     * 是否处理成功
     */
    private boolean isSuccess;

    /**
     * (系统级)错误代码
     */
    private String sysErrCode = "";

    /**
     * (系统级)错误描述
     */
    private String sysErrDesc = "";

    /**
     * (系统级)异常信息
     */
    private String sysException = "";

    /**
     * 处理耗时(毫秒)
     */
    private long elapsedMilliseconds;

    private String requestId;

    public DataResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public DataResult(T data) {
        this.data = data;
        this.isSuccess = true;
    }

    public DataResult(String errorCode, String errorDesc) {
        this.sysErrCode = errorCode;
        this.sysErrDesc = errorDesc;
        this.isSuccess = false;
    }

    public DataResult(String errorCode, String errorDesc, String errorException) {
        this.sysErrCode = errorCode;
        this.sysErrDesc = errorDesc;
        this.sysException = errorException;
        this.isSuccess = false;
    }

    public DataResult(String errorCode, String errorDesc, long elapsedMilliseconds) {
        this(errorCode, errorDesc);
        this.elapsedMilliseconds = elapsedMilliseconds;
    }

    public DataResult(String errorCode, String errorDesc, String errorException, long elapsedMilliseconds) {
        this(errorCode, errorDesc, errorException);
        this.elapsedMilliseconds = elapsedMilliseconds;
    }
}