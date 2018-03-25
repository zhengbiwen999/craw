package com.springboot.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:yangjunming
 * @date:08/12/2017.
 */
public enum LogUtil {

    INSTANCE;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void debug(String content) {
        logger.debug(content);
    }

    public void info(String content) {
        logger.info(content);
    }

    public void warn(String content) {
        logger.warn(content);
    }

    public void error(String content) {
        logger.warn(content);
    }
}
