package java8Time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class test {

    public static void main(String[] args) {
        // 获取当前日期
        LocalDate ld = LocalDate.now();
        System.out.println(ld);

        // 获取当前的年月日
        int year = ld.getYear();
        int month = ld.getMonthValue();
        int day = ld.getDayOfMonth();
        System.out.println(year + "-" + month + "-" + day);

        // 获取指定日期
        LocalDate lcl = LocalDate.of(2020, 2, 5);
        System.out.println(lcl);

        // 获取指定时间，具体到毫秒
        LocalDateTime lct = LocalDateTime.now();
        System.out.println(lct);

        // 从当前时间，向前或向后扩展
        LocalDate lde = LocalDate.now();
        // 向后数五天
        LocalDate lde1 = lde.plusDays(5);
        System.out.println(lde1);
        // 向前数五天
        LocalDate lde2 = lde.minusDays(5);
        System.out.println(lde2);
        // 向前数两周
        LocalDate lde3 = lde.minusWeeks(2);
        System.out.println(lde3);

        // 判断是否为闰年
        LocalDate date = LocalDate.now();
        if (date.isLeapYear()) {
            System.out.println(date + ":" + "是闰年");
        }

        // 获取两个日期之间的数据
        LocalDate date1 = LocalDate.of(2000, 10, 07);
        Period per = Period.between(date1, date);
        System.out.println("days:" + per.getDays());
        System.out.println("years:" + per.getYears());
        System.out.println("months:" + per.getMonths());

        //取当前时间戳
        Instant is = Instant.now();
        System.out.println(is);
        // java 8中的时钟
        Clock clock1 = Clock.systemDefaultZone();
        System.out.println(clock1);

        // 判断一个日期是在另一个日期前面还是后面
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2017, 3, 21);
        if (today.isBefore(tomorrow)) {
            System.out.println(true);
        }
        if (tomorrow.isAfter(today)) {
            System.out.println(true);
        }

        // 格式化输出日期
        String goodFriday = "1994-10-07";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate holiday = LocalDate.parse(goodFriday, formatter);
            System.out.println(holiday);
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
        }
        //将日期格式化，转化成字符串
        LocalDateTime ldtt = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:a");
        String str = ldtt.format(format);
        System.out.println(str);

        /**
         *  String -> Date
         */


        /**
         *  Date -> String
         */


        /**
         * 两个日期间
         */

        /**
         * 当月第一天
         */

    }

}
