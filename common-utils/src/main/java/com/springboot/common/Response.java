package com.springboot.common;

import com.springboot.utils.log.MwLogger;
import lombok.Data;
import org.slf4j.Logger;


/**
 * Created by yangjunming on 2017/7/15.
 */
@Data
public class Response {
    /**
     * 错误信息
     */
    private ErrorResult err = new ErrorResult(ErrorCode.SUCCESS, "SUCCES", "");
    private static Logger log = new MwLogger(Response.class);
}

