package com.fundation;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: Java JDK8.0前后时间日期常用类API
 * @create 2021/12/28 - 10:33
 */
public class H_DateTime {

    @Test
    public void dateTest() {
        /*
         * JDK8.0之前日期和时间的API测试:
         *   Ⅰ. System类中currentTimeMillis();
         *   Ⅱ. java.util.Date和子类java.sql.Date
         *   Ⅲ. SimpleDateFormat
         *   Ⅳ. Calendar
         *
         * 1.(JDK8.0之前)System类中的currentTimeMillis()
         *   返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差
         *   这个毫秒数又称为时间戳，不会重复(可以作为订单号)
         */
        System.out.println(System.currentTimeMillis()); // long类型毫秒数

        /*
         * 2.(JDK8.0之前)java.util.Date类
         *      |---java.sql.Date类 (匹配数据库中date类型)
         *   两个构造器的使用:
         *     ① -> Date(): 创建一个对应当前时间的Date对象
         *     ② -> Date(long millisecond): 创建指定毫秒数的Date对象
         *   两个方法的使用:
         *     ->toString(): 显示当前的年、月、日、时、分、秒
         *     ->getTime(): 获取当前Date对象对应的毫秒数(时间戳)
         */
        Date date1 = new Date();
        // util.Date类的toString方法: 返回年月日
        System.out.println(date1); // Tue Dec 28 12:35:01 CST 2021
        // util.Date类getTime()方法返回long毫秒数
        System.out.println(date1.getTime()); // 1640665724149
        // Date(long millisecond)构造器
        System.out.println(new Date(1640666101919L));

        /*
         * 3.(JDK8.0之前)java.sql.Date对应着数据库中的日期类型的变量
         *   java.util.Date是java.sql.Date的父类
         *   ->如何实例化
         *   java.sql.Date date2 = new java.sql.Date(1640666101919L);
         *   ->如何将java.util.Date对象转换为java.sql.Date对象
         */
        java.sql.Date date2 = new java.sql.Date(1640666101919L);
        // 只显示年-月-日
        System.out.println(date2); // 2021-12-28
        // 子类java.sql.Date --> 父类java.util.Date: 可以通过多态强转
        Date date3 = (Date) date2;
        System.out.println(date3);
        // 父类java.util.Date --> 子类java.sql.Date: 要通过getTime()作为参数
        Date date4 = new Date();
        java.sql.Date date5 = new java.sql.Date(date4.getTime());

    }

    @Test
    public void simpleDateFormatTest() throws ParseException {
        /*
         * 4.(JDK8.0之前)java.text.SimpleDateFormat对日期Date类的格式化和解析
         * ① SimpleDateFormat构造器的实例化
         * ② 格式化: 日期 -> 字符串
         *    解析(parse): 格式化的逆过程，字符串 -> 日期
         */
        // 使用默认构造器: (只能使用默认parse,format)
        SimpleDateFormat stf1 = new SimpleDateFormat();
        Date date1 = new Date();
        System.out.println(date1);    // Tue Dec 28 16:07:22 CST 2021
        // 格式化: 日期 -> 字符串
        String format1 = stf1.format(date1);
        System.out.println(format1);  // 2021/12/28 下午4:07
        // 解析: 字符串 -> 时间日期
        // String str = "2021-12-28";
        // Date dt = stf.parse(str); // 格式与默认不匹配不能解析

        // * 按照指定方式格式化和解析的SimpleDateFormat构造器
        SimpleDateFormat stf2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String format2 = stf2.format(date1);
        System.out.println(format2); // 2021/12/28 04:22:38
        // * 解析要求字符串必须是符合SimpleDateFormat识别的格式(通过构造器参数体现)
        // 否则，抛异常
        Date date2 = stf2.parse(format2);
        System.out.println(date2);   // Tue Dec 28 04:25:44 CST 2021
    }

    @Test
    public void calendarTest() {
        /*
         * 5.(JDK8.0之前)Calendar日历类(抽象类)
         * ① 实例化(Calendar是抽象类，不可直接实例化)
         *   Ⅰ.创建其子类(GregorianCalendar)的对象(用的少)
         *   Ⅱ.调用其静态方法getInstance() //和方法Ⅰ结果相同
         * ② 常用方法
         *   get()，得到某日期是第几天等信息
         *   set()，对calendar对象本身做修改
         *   add()，对calendar对象本身做修改
         *   getTime(): 日历类 -> Date
         *   setTime(): Date -> 日历类
         * ③ 注意: 月份一月是0，星期周日是1
         */
        // Calender的实例化方法
        Calendar calendar = Calendar.getInstance();

        // get()
        int days = calendar.get(Calendar.DAY_OF_MONTH); //当前是这个月第几天
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        // set()，使calendar本身修改
        calendar.set(Calendar.DAY_OF_MONTH, 22); //修改当前时间为这个月第22天
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        // add()，使calendar本身修改
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        // getTime(): 日历类 -> Date
        Date date = calendar.getTime();
        System.out.println(date);

        // setTime(): Date -> 日历类
        Date date1 = new Date();
        calendar.setTime(date1);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
    }

    @Test
    public void localDateTimeTest() {
        /*
         * Java JDK8.0之后新增了java.time包
         * 为了解决原有Date和Calendar的不足:
         *   Ⅰ.日期可变: Calendar子类对象实例日期可变
         *   Ⅱ.偏移性: Date年份从1990开始，月份从0开始
         *   Ⅲ.格式化只对Date有用，Calendar不行
         *   Ⅳ.线程不安全，不能处理闰秒...
         *
         * 主要运用java.time基础包和java.time.format
         */

        /*
         * 1.LocalDate,LocalTime,LocalDateTime
         *   ① LocalDateTime相较于LocalDate, LocalTime，使用频率更高
         *   ② 类似于Calendar
         *
         * now(): 获取当前的日期; 时间; 日期 + 时间
         * of(): 设置指定的年、月、日、时、分、秒 (没有偏移量)
         * getXxx(): 获取相关的属性(第几天，小时数...)
         * 体现不可变性: 返回值变化，对象本身不会变化:
         * withXxx(): 设置相关的属性，有返回值，对象本身不变
         * plusXxx()/minusXxx(): (可以传入负数)对时间加减，返回值，对象本身不变
         */

        // now()
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);     // 2021-12-28
        System.out.println(localTime);     // 18:03:22.371782900
        System.out.println(localDateTime); // 2021-12-28T18:03:22.371782900

        // of(时间)
        LocalDateTime localDateTime1 = LocalDateTime.of(2021,
                10, 6, 13, 23, 43);
        System.out.println(localDateTime1);

        // getXxx(): 获取相关的属性
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        // 体现不可变性 -> 返回值变化，对象本身不会变化
        // withXxx(): 设置相关的属性
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate);  // 2021-12-28
        System.out.println(localDate1); // 2021-12-22
        LocalDateTime localDateTime2 = localDateTime.withHour(4);
        System.out.println(localDateTime);  // 2021-12-28T18:12:19.672452100
        System.out.println(localDateTime2); // 2021-12-28T04:12:19.672452100

        // 对时间加减
        LocalDateTime localDateTime3 = localDateTime.plusMonths(-3);
        LocalDateTime localDateTime4 = localDateTime.minusDays(6);
    }

    @Test
    public void instantTest() {
        /*
         * 2.Instant类(类似于java.util.Date类)，用法类似LocalTime
         * ① 使用now()创建Instant对象
         * ② OffsetDateTime对象 =
              atOffset(ZoneOffset.ofHours(int hours)) 设置时区偏移量
         * ③ toEpochMilli(): 获取自1970年1月1日0时0分0秒(UTC)开始的毫秒数
         * ④ Instant.ofEpochMilli(long milliseconds): 通过给定的毫秒数，
              获取Instant实例，类似于Date(long milliseconds)
         */
        Instant ins = Instant.now();
        System.out.println("ins = " + ins); // 会差8小时，GMT标准时间

        // 添加时间的偏移量 +8小时
        OffsetDateTime offsetDateTime = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // toEpochMilli(): 获取自1970年1月1日0时0分0秒(UTC)开始的毫秒数
        long milli = ins.toEpochMilli();
        System.out.println(milli);

        // ofEpochMilli(): 通过给定的毫秒数，获取Instant实例(Date(long millis))
        Instant ins1 = Instant.ofEpochMilli(1550475314878L);
        System.out.println(ins1);
    }

    @Test
    public void dateTimeFormatterTest() {
        /*
         * 3.(JDK8.0新增)DateTimeFormatter类 (类似SimpleDateFormat)
         *   用于格式化和解析日期字符串
         * 实例化三种方式:
         * ①  预定义的标准格式如: ISO_LOCAL_DATE_TIME; ISO_LOCAL_DATE;
                ISO_LOCAL_TIME
         * ② 本地化相关的格式: ofLocalizedDateTime()
         # ③ 自定义的格式。ofPattern(“yyyy-MM-dd hh:mm:ss”) ⭐
         */
        // 实例化方式一: 预定义的标准格式: ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // 格式化:日期 -> 字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        // format()参数是LocalDate;LocalTime;LocalDatetime类型
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(str1); // 2019-02-18T15:42:18.797
        // 解析: 字符串 -> 日期
        TemporalAccessor parse = formatter.parse("2019-02-18T15:42:18.797");
        System.out.println(parse);

        // 方式二: 本地化相关的格式。如: ofLocalizedDateTime()
        // FormatStyle.LONG/.MEDIUM/.SHORT: 适用于LocalDateTime
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String str2 = formatter1.format(localDateTime);
        System.out.println(str2);// 2019年2月18日 下午03时47分16秒

        // ofLocalizedDate()
        // FormatStyle.FULL/.LONG/.MEDIUM/.SHORT: 适用于LocalDate
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        String str3 = formatter2.format(LocalDate.now());
        System.out.println(str3);// 2019-2-18

        // # 重点: 方式三: 自定义的格式。ofPattern(“yyyy-MM-dd hh:mm:ss”)
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        // 格式化
        String str4 = formatter3.format(LocalDateTime.now());
        System.out.println(str4); // 2019-02-18 03:52:09
        // 解析
        TemporalAccessor accessor = formatter3.parse("2019-02-18 03:52:09");
        System.out.println(accessor);
    }
}