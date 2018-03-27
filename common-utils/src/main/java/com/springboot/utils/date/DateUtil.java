package com.springboot.utils.date;

import com.springboot.utils.error.ThrowableUtil;
import com.springboot.utils.log.MwLogger;
import com.springboot.utils.number.NumberUtil;
import com.springboot.utils.regex.RegexUtil;
import com.springboot.utils.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.springframework.format.datetime.DateFormatter;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 */
public class DateUtil {


    private static Logger logger = new MwLogger(DateUtil.class);


    public static final long MILLISECONDS_SECOND = 1000;
    public static final long MILLISECONDS_MINUTE = 60000;
    public static final long MILLISECONDS_HOUR = 3600000;
    public static final long MILLISECONDS_DAY = 86400000;
    public static final long MILLISECONDS_WEEK = 604800000;
    public static final long SECONDS_MONTH = 2764800;
    public static final int SECONDS_HOUR = 3600;
    public static final int SECONDS_MINUTE = 60;
    public static final int MINUTE_HOUR = 60;

    public static final String DATE_SHORT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public final static String MM_dd_yyyy_HH_mm_ss = "MM/dd/yyyy HH:mm:ss";

    public final static String MM_dd_yyyy = "MM/dd/yyyy";

    public final static String yyyy_MM_dd = "yyyy-MM-dd";
    public final static String yyyy_MM = "yyyy-MM";
    public final static String HH_mm_ss = "HH:mm:ss";
    public final static String HH_mm = "HH:mm";
    public final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public final static String yyyyMMdd = "yyyyMMdd";
    public final static String yyyyMMddHH = "yyyyMMddHH";
    public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public final static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    private final static String[] weekEnNames = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

    private DateUtil() {
        //工具类无需对象实例化
    }

    static final Calendar calendar = Calendar.getInstance();

    /**
     * 获取字符串年份
     *
     * @param time
     * @return
     */
    public static String getYearString(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        if (time.length() > 1) {
            return time.substring(0, 4);
        }
        return time;
    }

    /**
     * 以yyyy-MM-dd HH:mm:ss格式显示当前时间
     *
     * @return
     * @throws ParseException
     */
    public static Date getNowDate() throws ParseException {
        return getString2Date(getNowDate2String());
    }

    /**
     * 得到当前日期的字符串
     *
     * @return
     */
    public static String getNowDate2String() {
        return DateFormatUtils.format(new Date(), yyyy_MM_dd_HH_mm_ss);
    }

    public static String getNowDate2String(String format) {
        return DateFormatUtils.format(new Date(), format);
    }

    public static String getTimestamp2String(Timestamp timestamp) {
        return DateFormatUtils.format(timestamp, yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * Date To String转化
     *
     * @param date
     * @return
     */
    public static String getDate2String(Date date) {
        return getDate2String(date, yyyy_MM_dd_HH_mm_ss);
    }

    public static String getDate2String(Date date, String format) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, format);
    }

    /**
     * ]
     * String To Date转化
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getString2Date(String date) throws ParseException {
        return getString2Date(date, yyyy_MM_dd_HH_mm_ss);
    }

    public static Date getString2Date(String date, String format) throws ParseException {
        String[] date_format = {format};
        return DateUtils.parseDate(date, date_format);
    }

    /**
     * 等效于php中time()的返回值
     *
     * @return 返回当前时间的总秒数(从1970-01-01 00:00:00算起)
     */
    public static long getPhpTime() {
        return System.currentTimeMillis() / MILLISECONDS_SECOND;
    }

    public static long getPhpTime(Date d) {
        if (d == null) {
            return 0;
        }
        return d.getTime() / MILLISECONDS_SECOND;
    }

    /**
     * 获取今天起始时间字符串
     *
     * @return
     */
    public static String getTodayBegin2String() {
        return DateFormatUtils.format(getTodayBegin(), yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 获取昨天起始时间字符串
     *
     * @return
     */
    public static String getYesterdayBegin2String() {
        return DateFormatUtils.format(getYesterdayBegin(), yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 获取今天结束时间字符串
     *
     * @return
     */
    public static String getTodayEnd2String() {
        return DateFormatUtils.format(getTodayEnd(), yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 获取昨天结束时间字段串
     *
     * @return
     */
    public static String getYesterdayEnd2String() {
        return DateFormatUtils.format(getYesterdayEnd(), yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 获取今天的开始时刻
     *
     * @return
     */
    public static Date getTodayBegin() {
        return getBegin4Date(new Date());
    }


    /**
     * 获取昨天的开始时刻
     *
     * @return
     */
    public static Date getYesterdayBegin() {
        return getBegin4Date(addDays(new Date(), -1));
    }

    /**
     * 获取今天的结束时刻
     *
     * @return
     */
    public static Date getTodayEnd() {
        return getEnd4Date(new Date());
    }

    /**
     * 获取昨天的结束时刻
     *
     * @return
     */
    public static Date getYesterdayEnd() {
        return getEnd4Date(addDays(new Date(), -1));
    }

    public static Date getBegin4Date(Date date) {
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar.getTime();
    }

    public static Date getEnd4Date(Date date) {
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime();
    }

    public static String getElapsedTime(Date startTime) {
        String elapsed_time = "0天 00:00:00";

        long left_milliSeconds = System.currentTimeMillis() - startTime.getTime();

        if (left_milliSeconds <= 0) {
            return elapsed_time;
        }

        long days = left_milliSeconds / DateUtil.MILLISECONDS_DAY;

        if (days < 0) {
            return elapsed_time;
        }

        long hours = (left_milliSeconds - days * DateUtil.MILLISECONDS_DAY) / DateUtil.MILLISECONDS_HOUR;

        if (hours < 0) {
            elapsed_time = days + "天 00:00:00";
            return elapsed_time;
        }

        long minutes = (left_milliSeconds - days * DateUtil.MILLISECONDS_DAY -
                hours * DateUtil.MILLISECONDS_HOUR) / DateUtil.MILLISECONDS_MINUTE;

        if (minutes < 0) {
            elapsed_time = days + "天 " + StringUtil.leftPad(hours + "", 2, '0') + ":00:00";
            return elapsed_time;
        }

        long seconds = (left_milliSeconds - days * DateUtil.MILLISECONDS_DAY -
                hours * DateUtil.MILLISECONDS_HOUR -
                minutes * DateUtil.MILLISECONDS_MINUTE) / DateUtil.MILLISECONDS_SECOND;

        if (seconds < 0) {
            elapsed_time = days + "天 " + StringUtil.leftPad(hours + "", 2, '0') + ":"
                    + StringUtil.leftPad(minutes + "", 2, '0') + ":00";
            return elapsed_time;
        }

        elapsed_time = days + "天 " + StringUtil.leftPad(hours + "", 2, '0') + ":"
                + StringUtil.leftPad(minutes + "", 2, '0') + ":" + StringUtil.leftPad(seconds + "", 2, '0');

        return elapsed_time;
    }

    public static String getLeftTime(Date deadline) {

        String left_time = "0天 00:00:00";

        long left_milliSeconds = deadline.getTime() - System.currentTimeMillis();

        if (left_milliSeconds <= 0) {
            return left_time;
        }

        long days = left_milliSeconds / DateUtil.MILLISECONDS_DAY;

        if (days < 0) {
            return left_time;
        }

        long hours = (left_milliSeconds - days * DateUtil.MILLISECONDS_DAY) / DateUtil.MILLISECONDS_HOUR;

        if (hours < 0) {
            left_time = days + "天 00:00:00";
            return left_time;
        }

        long minutes = (left_milliSeconds - days * DateUtil.MILLISECONDS_DAY -
                hours * DateUtil.MILLISECONDS_HOUR) / DateUtil.MILLISECONDS_MINUTE;

        if (minutes < 0) {
            left_time = days + "天 " + StringUtil.leftPad(hours + "", 2, '0') + ":00:00";
            return left_time;
        }

        long seconds = (left_milliSeconds - days * DateUtil.MILLISECONDS_DAY -
                hours * DateUtil.MILLISECONDS_HOUR -
                minutes * DateUtil.MILLISECONDS_MINUTE) / DateUtil.MILLISECONDS_SECOND;

        if (seconds < 0) {
            left_time = days + "天 " + StringUtil.leftPad(hours + "", 2, '0') + ":"
                    + StringUtil.leftPad(minutes + "", 2, '0') + ":00";
            return left_time;
        }

        left_time = days + "天 " + StringUtil.leftPad(hours + "", 2, '0') + ":"
                + StringUtil.leftPad(minutes + "", 2, '0') + ":" + StringUtil.leftPad(seconds + "", 2, '0');

        return left_time;
    }

    public static Date addMonths(Date src, int addMonths) {
        calendar.setTime(src);
        calendar.add(Calendar.MONTH, addMonths);
        return calendar.getTime();

    }

    public static Date addMonths(int addMonths) {
        return addMonths(new Date(), addMonths);

    }

    public static Date addDays(Date src, int addDays) {
        calendar.setTime(src);
        calendar.add(Calendar.DATE, addDays);
        return calendar.getTime();

    }

    public static Date addDays(int addDays) {
        return addDays(new Date(), addDays);

    }

    public static Date addHours(Date src, int addHours) {
        calendar.setTime(src);
        calendar.add(Calendar.HOUR, addHours);
        return calendar.getTime();

    }

    public static Date addHours(int addHours) {
        return addHours(new Date(), addHours);
    }

    public static Date addMinutes(Date src, int addMinutes) {
        calendar.setTime(src);
        calendar.add(Calendar.MINUTE, addMinutes);
        return calendar.getTime();
    }

    public static Date addMinutes(int addMinutes) {
        return addMinutes(new Date(), addMinutes);
    }

    public static Date addSeconds(Date src, int addSeconds) {
        calendar.setTime(src);
        calendar.add(Calendar.SECOND, addSeconds);
        return calendar.getTime();

    }

    public static Date addSeconds(int addSeconds) {
        return addSeconds(new Date(), addSeconds);

    }

    /**
     * 返回今天是星期几（按西方习惯，星期天返回0）
     *
     * @return
     */
    public static int getDayOfWeek() {
        return getDayOfWeek(new Date());
    }

    public static int getDayOfWeek(Date date) {
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day - 1;
    }

    /**
     * 返回今天星期几的英语缩写
     *
     * @return
     */
    public static String getWeekName() {
        return weekEnNames[getDayOfWeek()];
    }

    public static String getWeekName(Date date) {
        return weekEnNames[getDayOfWeek(date)];
    }

    /**
     * 按中国习惯，返回今天是星期几(星期天返回7)
     *
     * @return
     */
    public static int getDayOfWeekCN(Date date) {
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {//外国，星期天为每个星期的第1天
            return 7;
        }
        return day - 1;
    }

    /**
     * 格式化日期输出
     *
     * @param src
     * @param formatPattern
     * @return
     */
    public static String formatDate(Date src, String formatPattern) {
        if (src == null) {
            return "";
        }
        DateFormat fmt = new SimpleDateFormat(formatPattern);
        return fmt.format(src);
    }

    public static String formatDate(Date src) {
        return formatDate(src, yyyy_MM_dd_HH_mm_ss);
    }

    public static String formatDate() {
        return formatDate(new Date(), yyyy_MM_dd);
    }

    public static String formatDate(String formatPattern) {
        return formatDate(new Date(), formatPattern);
    }

    public static Date getDate(int year, int month, int day) {
        calendar.clear();
        calendar.set(year, month - 1, day);
        return calendar.getTime();

    }

    public static Date parseDate(String dateString, String formatPattern) throws Exception {
        return parseDate(dateString, formatPattern, null, false);
    }

    public static Date parseDate(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        try {
            if (RegexUtil.isDateTime(dateString)) {
                return parseDate(dateString, yyyy_MM_dd_HH_mm_ss);
            } else if (RegexUtil.isDate(dateString)) {
                return parseDate(dateString, yyyy_MM_dd);
            }
            return timestamp2Date(dateString, null);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDate(String dateString, boolean throwExceptionWhenParseFail) throws Exception {
        return parseDate(dateString, yyyy_MM_dd_HH_mm_ss, null, throwExceptionWhenParseFail);
    }

    public static Date parseDate(String dateString, String formatPattern, Date defaultValue, boolean throwExceptionWhenParseFail) throws Exception {
        try {
            if (StringUtils.isEmpty(dateString) || StringUtils.isEmpty(formatPattern)) {
                return null;
            }
            DateFormatter dateFormatter = new DateFormatter();
            dateFormatter.setPattern(formatPattern);
            return dateFormatter.parse(dateString, Locale.CHINA);
        } catch (Exception e) {
            logger.error("DateUtil.parseDate出错:" + ThrowableUtil.getStackTrace(e) + ",dateString:" + dateString + ",formatPattern:" + formatPattern);
            if (throwExceptionWhenParseFail) {
                throw e;
            }
        }
        return defaultValue;
    }


    /**
     * 将unix时间截转换成Date
     *
     * @param timestamp
     * @param defaultValue
     * @return
     */
    public static Date timestamp2Date(String timestamp, Date defaultValue) {
        if (StringUtils.isEmpty(timestamp)) {
            return defaultValue;
        }
        timestamp = timestamp.trim();
        //1486270737
        if (timestamp.length() == 10) { //秒
            timestamp += "000";
        }
        Long time = NumberUtil.getLongValue(timestamp);
        return new Date(time);
    }

    public static Date timestamp2Date(String timestamp) {
        return timestamp2Date(timestamp, new Date());
    }

    public static String formatTimestamp(String timestamp) {
        return formatDate(timestamp2Date(timestamp, new Date()));
    }

    public static String formatTimestamp(String timestamp, String formatter) {
        return formatDate(timestamp2Date(timestamp, new Date()), formatter);
    }

    /**
     * 得到指定日期是几号
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到今天是几号
     *
     * @return
     */
    public static int getDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到指定日期的月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH);
    }


    public static Date getMinDate(Date date1, Date date2) {
        return date1.getTime() < date2.getTime() ? date1 : date2;
    }

    public static long parseSecond(String date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = yyyy_MM_dd_HH_mm_ss;
        }
        Date date1 = null;
        if (StringUtils.isNumeric(date) && date.length() == 13) {
            date1 = new Date(NumberUtil.getLongValue(date));
        } else {
            try {
                date1 = parseDate(date, format);
            } catch (Exception e) {
                logger.error("DateUtil.parseDate出错:" + ThrowableUtil.getStackTrace(e) + ",dateString:" + date + ",formatPattern:" + format);
                return getPhpTime();
            }
        }
        if (date1 == null) {
            return getPhpTime();
        }
        return date1.getTime() / MILLISECONDS_SECOND;
    }

    public static Date getDate(String formatPattern) {

        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        try {
            return parseDate(format.format(new Date()), formatPattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDate(int min) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, min);
        return c.getTime();
    }

    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getCurrTotalMinute(Date date) {
        return getHour(date) * MINUTE_HOUR + getMinute(date);
    }

    public static int getDiffDay(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff = time1 - time2;
        return (int) (diff / MILLISECONDS_DAY);
    }

    public static int getDiffMinutes(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff = time1 - time2;
        return (int) (diff / MILLISECONDS_MINUTE);
    }

    public static int getDiffHours(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff = time1 - time2;
        return (int) (diff / MILLISECONDS_HOUR);
    }

    public static int getDiffWeeks(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff = time1 - time2;
        return (int) (diff / MILLISECONDS_WEEK);
    }


    @Deprecated
    public static int minutesBetween(Date sDate, Date eDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(sDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(eDate);
        long time2 = cal.getTimeInMillis();
        long minutesBetween = (time2 - time1) / (MILLISECONDS_MINUTE);
        return Integer.valueOf(String.valueOf(minutesBetween));
    }

    public static Boolean isBefore(String sDate, String eDate) {
        Date sDate1 = null;
        Date eDate1 = null;
        try {
            sDate1 = getString2Date(sDate);
            eDate1 = getString2Date(eDate);
        } catch (ParseException e) {
            logger.info("时间比较出错");
            return null;
        }
        if (null == sDate1 || null == eDate1) {
            logger.info("时间比较出错");
            return null;
        }
        if (sDate1.before(eDate1)) {
            return true;
        }
        return false;
    }


    /**
     * @return 当前月的第一天日期
     */
    public static String getFirstDayOfCurrentMouth(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return getDate2String(c.getTime(), "yyyy-MM-dd");
    }

    /**
     * @return 当前月的最后一天日期
     */
    public static String getLastDayOfCurrentMouth(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return DateUtil.getDate2String(c.getTime(),"yyyy-MM-dd");
    }

    /**
     * @param index
     * @return  获取上周第一天和最后一天,index为1，结果为上周的第一天日期，index为7，结果为上周的最后一天日期
     */
    public static String getPreWeekTime(int index) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = index - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 7);
        return getDate2String(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * 将second 装换成hh:mm:ss格式
     * @param seconds 秒数
     * @return hh:mm:ss
     */
    public static String parseSecondToTime(int seconds) {
        int hour = seconds / SECONDS_HOUR;
        int minute = (seconds % SECONDS_HOUR) / SECONDS_MINUTE;
        int second = seconds - hour * SECONDS_HOUR - minute * SECONDS_MINUTE;
        Time time = new Time(hour, minute, second);
        return time.toString();
    }



//    /**
//     * 将一个符合指定格式的字串解析成日期型
//     *
//     * @param aDateStr
//     * @param formatter
//     * @return
//     * @throws ParseException
//     */
//    public static final Date parser(String aDateStr, String formatter) throws ParseException {
//        if (org.apache.commons.lang.StringUtils.isBlank(aDateStr)) return null;
//        Assert.hasText(formatter);
//        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
//        return sdf.parse(aDateStr);
//    }

    public static void main(String[] args) {
//        System.out.println(getDiffDay(DateUtil.parseDate("2016-08-01 23:59:59"), DateUtil.parseDate("2016-08-01 00:00:00")));
//        System.out.println(minutesBetween(DateUtil.parseDate("2016-09-23 13:59:00"), DateUtil.parseDate("2016-09-23 19:01:00")));
//        System.out.printf(DateUtil.formatDate(DateUtil.timestamp2Date("1486269232000")));
//        System.out.printf(DateUtil.formatTimestamp("1486269232000"));
        //System.out.println(DateUtil.parseDate("2017-03-31"));
        System.out.println(parseSecondToTime(0));

//        System.out.println(RegexUtil.isDateTime("2017-07-08 23:59:59"));
//        System.out.println(parseDate("2017-07-08 23:59:59").getTime());

//        Date d1 = DateUtil.parseDate("2017-07-08 23:59:59");
//        Date d2 = DateUtil.parseDate("2017-03-29 18:01:03");
//
//        System.out.println(getDiffHours(d1, d2));
//
//        System.out.println(DateUtil.parseDate("1491809589"));

//        System.out.println(DateUtil.parseDate("2017-03-31 18:01:03"));
//
//        System.out.println(DateUtil.parseDate("2017-03-31 18:01:03"));
    }
}
