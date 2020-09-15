package com.baize.framework.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;
import sun.swing.StringUIClientPropertyKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lubinjia
 * @create 2020/9/15 21:26
 */
public class DateUtils {
    public static final ThreadLocal<SimpleDateFormat> SDF_TIME = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static final ThreadLocal<SimpleDateFormat> SDF_DATE = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd"));
    public static final ThreadLocal<SimpleDateFormat> SDF_MONTH = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM"));

    private static final SimpleDateFormat SDF_MONTH_DAY = new SimpleDateFormat("MM-dd");

    /**
     * 转换日期类型，只取日期部分，舍弃时分秒。如2020-09-01 12:02:23 -> 2020-090-01 00:00:00
     */
    public static Date parseToDate(Date date) throws ParseException {
        if (date == null) {
            return null;
//            throw new NullPointerException("传入的日期为null！");
        }
        String str = SDF_DATE.get().format(date);
        return SDF_DATE.get().parse(str);
    }

    /**
     * 根据字符串转换成时间类型：yyyy-MM-dd
     */
    public static Date parseToDate(String dateStr) throws ParseException {
        if(StringUtils.isEmpty(dateStr)){
            return null;
        }
        return SDF_DATE.get().parse(dateStr);
    }

    /**
     * 根据字符串转换成时间类型：yyyy-MM-dd HH:mm:ss
     */
    public static Date parseToDateTime(String dateStr) throws ParseException {
        if(StringUtils.isEmpty(dateStr)){
            return null;
        }
        return SDF_TIME.get().parse(dateStr);
    }

    /**
     * 判断日期是否为空值，此处的场景1900-01-01表示日期为空
     */
    public static boolean isNull(Date date) throws ParseException {
        if (date == null) {
            return true;
        }
        Date dateByDay = parseToDate(date);
        Date nullDay = parseToDate("1900-01-01");
        return dateByDay.compareTo(nullDay) == 0;
    }

    /**
     * 格式化成日期格式的字符串：yyyy-MM-dd
     */
    public static String formatToDate(Date date){
        if (date == null) {
            return Strings.EMPTY;
        }
        return SDF_DATE.get().format(date);
    }

    /**
     * 格式化成日期-时间格式的字符串：yyyy-MM-dd HH:mm:ss
     */
    public static String formatToDateTime(Date date){
        if (date == null) {
            return Strings.EMPTY;
        }
        return SDF_TIME.get().format(date);
    }

    /**
     * 格式化成年-月格式的字符串：yyyy-MM
     */
    public static String formatToMonth(Date date){
        if (date == null) {
            return Strings.EMPTY;
        }
        return SDF_MONTH.get().format(date);
    }

    /**
     * 格式化成月-日格式的字符串：MM-dd
     */
    public static String formatToMonthDay(Date date){
        if (date == null) {
            return Strings.EMPTY;
        }
        return SDF_MONTH_DAY.format(date);
    }

    /**
     * 格式化日期字符串：年-季度 yyyy-Qn 如 2020-Q3
     */
    public static String formatToYearQuarter(Date date){
        LocalDate localDate = dateToLocalDate(date);
        Month month = localDate.getMonth();
        int quarter;
        if (month.getValue() % 3 > 0) {
            quarter = month.getValue() / 3 + 1;
        } else {
            quarter = month.getValue() / 3;
        }
        return localDate.getYear() + "-Q" + quarter;
    }

    /**
     * 获取两个日期之间的天数差
     */
    public static long getDiffDays(Date fromDate, Date toDate){
        LocalDate beginDate = dateToLocalDate(fromDate);
        LocalDate endDate = dateToLocalDate(toDate);
        return ChronoUnit.DAYS.between(beginDate, endDate);
    }

    /**
     * 获取两个日期之间的月份差
     * 只要跨月就算相差一个月，不管日期差是否满30天，即不满一月算一月
     */
    public static long getDiffMonths(Date fromDate, Date toDate){
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);
        int fromMonth = from.get(Calendar.MONTH);
        int toMonth = from.get(Calendar.MONTH);
        return toMonth - fromMonth;
    }

    /**
     * 获取两个日期之间的月份差
     * 计算自然月，相差天数超过30天时才算相差一个月
     */
    public static long getDiffMonthsOfNatural(Date fromDate, Date toDate){
        LocalDate beginDate = dateToLocalDate(fromDate);
        LocalDate endDate = dateToLocalDate(toDate);
        return ChronoUnit.MONTHS.between(beginDate, endDate);
    }

    /**
     * 获取两个日期之间的周差
     * 只要跨周就算相差一周，不管日期差是否满7天，即不满一周算一周
     */
    public static long getDiffWeeks(Date fromDate, Date toDate){
        return (fromDate.getTime() - toDate.getTime()) / (1000 * 3600L * 24L * 7L);
    }

    /**
     * 获取两个日期之间的周差
     * 计算自然周，相差天数超过7天时才算相差一周
     */
    public static long getDiffWeeksOfNatural(Date fromDate, Date toDate){
        LocalDate beginDate = dateToLocalDate(fromDate);
        LocalDate endDate = dateToLocalDate(toDate);
        return ChronoUnit.WEEKS.between(beginDate, endDate);
    }

    /**
     * 获取两个日期之间的季度差
     * 计算自然季度，相差天数超过3个自然月时才算相差一个季度
     */
    public static long getDiffQuartersOfNatural(Date fromDate, Date toDate){
        long diffMonths = getDiffMonthsOfNatural(fromDate, toDate);
        return diffMonths / 3;
    }


    public static LocalDate dateToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 日期加法
     */
    public static Date addDays(Date date, long daysToAdd){
        LocalDate localDate = dateToLocalDate(date);
        return localDateToDate(localDate.plusDays(daysToAdd));
    }

    /**
     * 日期减法
     */
    public static Date minusDays(Date date, long daysToMinus){
        LocalDate localDate = dateToLocalDate(date);
        return localDateToDate(localDate.minusDays(daysToMinus));
    }

}
