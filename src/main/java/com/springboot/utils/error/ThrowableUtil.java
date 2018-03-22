package com.springboot.utils.error;



import com.springboot.utils.string.StringUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * {type your description }
 *
 * @since: 15/11/3.
 * @author: yangjunming
 */
public class ThrowableUtil {

    /**
     * 获取异常的完整信息
     *
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        return getStackTrace(t, true, -1);
    }

    /**
     * 获取异常的部分信息
     *
     * @param t
     * @Param length 堆栈长度 <=0 表示获取完整长度
     * @return
     */
    public static String getStackTrace(Throwable t, int length) {
        return getStackTrace(t, true, length);
    }

    public static String getStackTrace(Throwable t, boolean removeLF) {
        return getStackTrace(t,removeLF, -1);
    }

    public static String getStackTrace(Throwable t, boolean removeLF, int length) {
        if (t == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            String stackTrace = sw.toString();
            if (removeLF) {
                stackTrace = StringUtil.replaceBlank(stackTrace);
            }
            if (length <= 0) {
                return stackTrace;
            } else {
                int len = stackTrace.length() >= length ? length : stackTrace.length();
                return stackTrace.substring(0, len);
            }
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
