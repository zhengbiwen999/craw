package com.springboot.utils.number;


import com.springboot.utils.log.MwLogger;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.format.number.NumberStyleFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class NumberUtil {

    private static Logger logger = new MwLogger(NumberUtil.class);

    private final static char[] digits = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P',
            'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'};


    /**
     * 将十进制的数字转换为指定进制的字符串。
     *
     * @param i      十进制的数字。
     * @param system 指定的进制，常见的2/8/16/32。
     * @return 转换后的字符串。
     */
    public static String numericToString(long i, byte system) {
        long num = 0;
        if (i < 0) {
            num = ((long) 2 * 0x7fffffff) + i + 2;
        } else {
            num = i;
        }
        char[] buf = new char[32];
        byte charPos = 32;
        while ((num / system) > 0) {
            buf[--charPos] = digits[(int) (num % system)];
            num /= system;
        }
        buf[--charPos] = digits[(int) (num % system)];
        return new String(buf, charPos, (32 - charPos));
    }


    /**
     * 将其它进制的数字（字符串形式）转换为十进制的数字。
     *
     * @param s      其它进制的数字（字符串形式）
     * @param system 指定的进制，常见的2/8/16/32。
     * @return 转换后的数字。
     */
    public static long stringToNumeric(String s, byte system) {
        char[] buf = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        long num = 0;
        for (int i = 0; i < buf.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == buf[i]) {
                    num += j * Math.pow(system, buf.length - i - 1);
                    break;
                }
            }
        }
        return num;
    }

    private NumberUtil() {
        //工具类无需对象实例化
    }


    @SuppressWarnings("unchecked")
    public static <T extends Number> T parseNumber(String text,
                                                   Class<T> targetClass) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        Number n = null;
        try {
            n = org.springframework.util.NumberUtils.parseNumber(text,
                    targetClass);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return (T) n;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T parseNumber(Object obj,
                                                   Class<T> targetClass) {

        if (obj == null) {
            return null;
        }
        if (StringUtils.isEmpty(obj.toString())) {
            return null;
        }
        Number n = null;
        try {
            n = org.springframework.util.NumberUtils.parseNumber(obj.toString(),
                    targetClass);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return (T) n;
    }

    public static long getLongValue(Long v) {
        if (v == null) {
            return 0;
        }
        return v.longValue();
    }

    public static long getLongValue(Integer b) {
        if (b == null) {
            return 0;
        }
        return b.longValue();
    }

    public static long getLongValue(BigDecimal v) {
        if (v == null) {
            return 0;
        }
        return v.longValue();
    }

    public static int getIntValue(BigDecimal b) {
        if (b == null) {
            return 0;
        }
        return b.intValue();
    }

    public static int getIntValue(Integer b) {
        if (b == null) {
            return 0;
        }
        return b.intValue();
    }

    public static int getIntValue(Long b) {
        if (b == null) {
            return 0;
        }

        if (b.longValue() > Integer.MAX_VALUE || b.longValue() < Integer.MIN_VALUE) {
            throw new NumberFormatException("Long转化BInteger, 越界...");
        }
        return b.intValue();
    }

    public static int getIntValue(Byte b) {
        if (b == null) {
            return 0;
        }
        return b.intValue();
    }

    public static byte getByteValue(Byte b) {
        if (b == null) {
            return 0;
        }
        return b.byteValue();
    }

    public static byte getByteValue(Integer b) {
        if (b == null) {
            return 0;
        }
        if (b.intValue() > Byte.MAX_VALUE || b.intValue() < Byte.MIN_VALUE) {
            throw new NumberFormatException("Integer转化Byte, 越界...");
        }
        return b.byteValue();
    }

    public static byte getByteValue(Long b) {
        if (b == null) {
            return 0;
        }

        if (b.longValue() > Byte.MAX_VALUE || b.longValue() < Byte.MIN_VALUE) {
            throw new NumberFormatException("Long转化Byte, 越界...");
        }
        return b.byteValue();
    }

    public static short getShortValue(Integer b) {
        if (b == null) {
            return 0;
        }

        if (b.intValue() > Short.MAX_VALUE || b.intValue() < Short.MIN_VALUE) {
            throw new NumberFormatException("Integer转化Short, 越界...");
        }
        return b.shortValue();
    }

    public static short getShortValue(Short b) {
        if (b == null) {
            return 0;
        }
        return b.shortValue();
    }

    public static double getDoubleValue(Double b) {
        if (b == null) {
            return 0;
        }
        return b.doubleValue();
    }

    public static double getDoubleValue(BigDecimal b) {
        if (b == null) {
            return 0;
        }
        return b.doubleValue();
    }

    public static BigDecimal getBiDecimal(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return new BigDecimal(s);
    }


    public static BigDecimal getBiDecimal(Integer s) {
        if (s == null) {
            return null;
        }
        return new BigDecimal(s);
    }

    public static BigDecimal getBiDecimal(Double s) {
        if (s == null) {
            return null;
        }
        return new BigDecimal(s);
    }

    public static Long getLongValue(BigDecimal b, Boolean nullToZero) {
        if (b == null) {
            if (nullToZero) {
                return 0L;
            }
            return null;
        }
        return b.longValue();
    }

    /**
     * 数字格式化
     *
     * @param num
     * @param pattern 比如：#,###.00
     * @param locale
     * @return
     */
    public static String formatNumber(Number num, String pattern, Locale locale) {
        if (num == null) {
            return null;
        }
        NumberStyleFormatter formatter = new NumberStyleFormatter();
        formatter.setPattern(pattern);
        return formatter.print(num, locale);
    }

    public static String formatNumber(Number num, String pattern) {
        if (num == null) {
            return null;
        }
        NumberStyleFormatter formatter = new NumberStyleFormatter();
        formatter.setPattern(pattern);
        return formatter.print(num, Locale.SIMPLIFIED_CHINESE);
    }

    public static double setScale(Double b, int scale) {
        if (b == null) {
            return 0;
        }
        return setScale(b.doubleValue(), scale);
    }

    public static double setScale(BigDecimal b, int scale) {
        if (b == null) {
            return 0;
        }
        return setScale(b.doubleValue(), scale);
    }

    public static double setScale(double b, int scale) {
        BigDecimal decimal = new BigDecimal(b);
        return decimal.setScale(scale + 1, RoundingMode.HALF_UP).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 数字格式化
     *
     * @param num
     * @return
     */
    public static String formatNumber(Number num) {
        return formatNumber(num, "#.##", Locale.SIMPLIFIED_CHINESE);
    }

    public static String getString(Short num) {
        if (num == null) {
            return "";
        }
        return num + "";
    }

    public static String getString(Integer num) {
        if (num == null) {
            return "";
        }
        return num + "";
    }

    public static String getString(Long num) {
        if (num == null) {
            return "";
        }
        return num + "";
    }

    public static String getString(Double num) {
        if (num == null) {
            return "";
        }
        return num + "";
    }

    public static String getString(Float num) {
        if (num == null) {
            return "";
        }
        return num + "";
    }

    public static byte getByteValue(String str) {
        return getByteValue(str, (byte) 0);
    }

    public static byte getByteValue(String str, byte defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }
        return Byte.valueOf(str);
    }

    public static short getShortValue(String str) {
        return getShortValue(str, (short) 0);
    }

    public static short getShortValue(String str, short defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }
        return Short.valueOf(str).shortValue();
    }

    public static int getIntValue(String str) {
        return getIntValue(str, 0);
    }

    public static int getIntValue(String str, int defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }
        return Integer.valueOf(str).intValue();
    }

    public static int getIntValue(Object obj) {
        if (obj == null) {
            return 0;
        }
        return getIntValue(obj.toString());
    }

    public static int getIntValue(Object obj, int defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        return getIntValue(obj.toString(), defaultValue);
    }

    public static long getLongValue(String str) {
        return getLongValue(str, 0L);
    }

    public static long getLongValue(String str, long defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }
        return Long.valueOf(str).longValue();
    }

    public static double getDoubleValue(String str, double defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }
        return Double.valueOf(str).doubleValue();
    }

    public static double getDoubleValue(double value, int length) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.setScale(length, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static BigDecimal getBiDecimal(Object object) {
        if (object == null) {
            return null;
        }
        return getBiDecimal(object.toString());
    }

    public static long getLongValue(Object obj) {
        if (obj == null) {
            return 0;
        }
        return getLongValue(obj.toString());
    }

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        BigDecimal result = new BigDecimal(0);
        if (a != null) {
            result = result.add(a);
        }
        if (b != null) {
            result = result.add(b);
        }
        return result;
    }

    /**
     * 获取2位小数的字符串表示
     *
     * @return
     */
    public static String getString2(BigDecimal a) {
        return setScale(a, 2) + "";
    }

}
