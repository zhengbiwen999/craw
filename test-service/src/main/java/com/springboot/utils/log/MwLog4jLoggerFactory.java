package com.springboot.utils.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.spi.AbstractLoggerAdapter;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.util.ReflectionUtil;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 *
 * MwLog4jLogger's Factory implementation
 *
 * Created by Jacobs Lei on 2017/12/1.
 *
 * @author Jacobs Lei
 */
public class MwLog4jLoggerFactory extends AbstractLoggerAdapter<Logger> implements ILoggerFactory {

    private static final String FQCN = MwLog4jLoggerFactory.class.getName();
    private static final String PACKAGE = "org.slf4j";

    @Override
    protected Logger newLogger(String name, LoggerContext context) {
        final String key = Logger.ROOT_LOGGER_NAME.equals(name) ? LogManager.ROOT_LOGGER_NAME : name;
        return new MwLog4jLogger(context.getLogger(key), name);
    }

    @Override
    protected LoggerContext getContext() {
        final Class<?> anchor = ReflectionUtil.getCallerClass(FQCN, PACKAGE);
        return anchor == null ? LogManager.getContext() : getContext(ReflectionUtil.getCallerClass(anchor));
    }

    private static final class MwLog4jLoggerFactoryHolder {
        private static MwLog4jLoggerFactory INSTANCE = new MwLog4jLoggerFactory();
    }

    public static MwLog4jLoggerFactory getSingleton(){
        return MwLog4jLoggerFactoryHolder.INSTANCE;
    }

    private MwLog4jLoggerFactory(){

    }
}
