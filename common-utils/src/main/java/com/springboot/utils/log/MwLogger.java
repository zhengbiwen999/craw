package com.springboot.utils.log;



import com.springboot.utils.error.ThrowableUtil;
import com.springboot.utils.json.FastJsonUtil;
import com.springboot.utils.json.JsonUtil;
import com.springboot.utils.string.StringUtil;
import com.springboot.utils.trace.TraceUtil;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.util.StringUtils;


/**
 * Created by yangjunming on 7/9/16.
 */
public class MwLogger implements Logger {

    private final Logger logger;

    private final static String QUOTE = "\"";

    private final static String QUOTE_REPLACE = "`";

    private final static String SINGLE_QUOTE = "\\`";

    private final static String ENTER = "\n";

    private final static String WRAPPER = "";

    private static final JsonUtil jsonUtil = new FastJsonUtil();


    private final Class<?> clazz;

    public MwLogger(Class clazz) {
        this.clazz = clazz;
        this.logger = MwLog4jLoggerFactory.getSingleton().getLogger(clazz.getName());
    }

    public String formatLogData(LogData data) {
        if (data == null) {
            return "";
        }

        data.setRequestId(TraceUtil.INSTANCE.getRequestId());

        String src = StringUtil.replaceBlank(jsonUtil.toJson(data));
        if (StringUtils.isEmpty(src)) {
            return "";
        }

        if (!StringUtils.isEmpty(ENTER) && src.contains(ENTER)) {
            return src.replace(ENTER, "");
        }

        if (src.contains("\\")) {
            src = src.replace("\\", "");
        }

        if (!StringUtils.isEmpty(QUOTE) && src.contains(QUOTE)) {
            return src.replace(QUOTE, QUOTE_REPLACE);
        }

        return src;
    }

    public String replaceQuote(String src) {
        if (StringUtils.isEmpty(src)) {
            return "";
        }
        if (!StringUtils.isEmpty(QUOTE) && src.contains(QUOTE)) {
            src = src.replace(QUOTE, QUOTE_REPLACE).replace(SINGLE_QUOTE, QUOTE_REPLACE);
        }

        if (!StringUtils.isEmpty(ENTER) && src.contains(ENTER)) {
            src = src.replace(ENTER, "");
        }
        src = src.replace("  ", "");

        return TraceUtil.INSTANCE.getRequestId() + " " + StringUtil.replaceBlank(src);
    }

    @Override
    public String getName() {
        return clazz.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void trace(String msg) {
        logger.trace(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void trace(String format, Object arg) {
        logger.trace(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arguments) + WRAPPER);
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return true;
    }

    @Override
    public void trace(Marker marker, String msg) {
        logger.trace(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        logger.trace(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, argArray) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(String msg) {
        logger.debug(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void debug(String format, Object arg) {
        logger.debug(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arguments) + WRAPPER);
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return true;
    }

    public void debug(LogData logData) {
        logger.debug(formatLogData(logData));
    }

    @Override
    public void debug(Marker marker, String msg) {
        logger.debug(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String format, Object... argArray) {
        logger.debug(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, argArray) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(String msg) {
        logger.info(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void info(String format, Object arg) {
        logger.info(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arguments) + WRAPPER);
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.info(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return true;
    }

    public void info(LogData logData) {
        logger.info(formatLogData(logData));
    }

    @Override
    public void info(Marker marker, String msg) {
        logger.info(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        logger.info(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String format, Object... argArray) {
        logger.info(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, argArray) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        logger.info(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(String msg) {
        logger.warn(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void warn(String format, Object arg) {
        logger.warn(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arguments) + WRAPPER);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return true;
    }

    public void warn(LogData logData) {
        logger.warn(formatLogData(logData));
    }

    @Override
    public void warn(Marker marker, String msg) {
        logger.warn(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String format, Object... argArray) {
        logger.warn(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, argArray) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(String msg) {
        logger.error(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void error(String format, Object arg) {
        logger.error(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.error(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arguments) + WRAPPER);
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return true;
    }

    public void error(LogData logData) {
        logger.error(formatLogData(logData));
    }

    @Override
    public void error(Marker marker, String msg) {
        logger.error(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        logger.error(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String format, Object... argArray) {
        logger.error(marker, WRAPPER + TraceUtil.INSTANCE.getRequestId() + " " + String.format(format, argArray) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        logger.error(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }


}
