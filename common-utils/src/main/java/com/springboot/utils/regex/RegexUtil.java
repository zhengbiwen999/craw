package com.springboot.utils.regex;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 */
public class RegexUtil {

    /**
     * YYYY-MM-DD日期格式的校验正则表达式
     */
    public static final Pattern DATE_PATTERN_YYYY_MM_DD = Pattern.compile("^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$");


    private RegexUtil() {
        //工具类无需对象实例化
    }

    /**
     * 是否匹配指定正则表达式
     *
     * @param source
     * @param pattern
     * @return
     */
    public static boolean isMatch(String source, String pattern) {
        return isMatch(source, Pattern.compile(pattern));
    }

    public static boolean isMatch(String source, Pattern pattern) {
        return pattern.matcher(source).find();
    }

    /**
     * 返回正式表达式匹配的第1组内容
     *
     * @param source
     * @param regexPattern
     * @return
     */
    public static String getMatchFirstGroupValue(String source,
                                                 String regexPattern) {
        Pattern r = Pattern.compile(regexPattern);
        Matcher m = r.matcher(source);
        if (m.find()) {
            return m.group();
        }
        return null;
    }

    /**
     * 格式化回车换行符（注：windows中换行\r\n，linux下为\n，本方法统一替换成\n）
     *
     * @param src
     * @return
     */
    public static String formatReturn(String src) {
        return src.replaceAll("[\n|\r|\r\n|\n\r]+", "\n");
    }

    /**
     * 判断是否手机号
     *
     * @param src
     * @return
     */
    public static boolean isMobileNumber(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        return isMatch(src, "(^(13\\d|15[^4,\\D]|17[13678]|18\\d)\\d{8}|170[^346,\\D]\\d{7})$");
    }

    /**
     * 判断是否为 yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param src
     * @return
     */
    public static boolean isDateTime(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        return isMatch(src, "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$");
    }

    /**
     * 判断是否为 yyyy-MM-dd格式的字符串
     *
     * @param src
     * @return
     */
    public static boolean isDate(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        return isMatch(src, "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$");
    }

    /**
     * 判断是否为数字
     *
     * @param src
     * @return
     */
    public static boolean isNumber(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        return isMatch(src, "^[0-9]*$");
    }

    /**
     * 判断是否为JSON字符串
     *
     * @param src
     * @return
     */
    public static boolean isJson(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        return isMatch(src, "(?<=\\{)\\s*[^{]*?(?=[\\},])");
    }


//    /**
//     * 去掉thrift对象json字符串中的setXXX属性
//     *
//     * @param json
//     * @return
//     */
    public static String removeThriftSetProperty(String json) {
        return json.replaceAll("\\\"set[\\w]+\\\":[ ]{0,}(true|false),{0,1}", "")
                .replaceAll("\"[\\w]+Iterator\":\\[[\\{\\\"$\\w:.\\[\\]\\},]{0,}],{0,1}", "")
                .replaceAll(",}", "}");
    }


    public static void main(String[] args) {
        String s = "2009-10-01";
        System.out.println(isDate(s));
        s = "2009-10-41";
        System.out.println(isDate(s));
        s = "2009-10-21 12:00:00";
        System.out.println(isDateTime(s));
        s = "2009-10-21 12:00:70";
        System.out.println(isDateTime(s));
    }
}
