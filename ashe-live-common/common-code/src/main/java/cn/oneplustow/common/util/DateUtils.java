package cn.oneplustow.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author ruoyi
 */
public class DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    private static final String[] DEFAULT_PARSE_PATTERNS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyyMMdd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm","HH:mm","HH:mm:ss", "yyyy-MM"};

    public static LocalDateTime parseLocalDateTime(final String str) throws ParseException {
        Date parseDate = parseDate(str, DEFAULT_PARSE_PATTERNS);
        return toLocalDateTime(parseDate);
    }

    public static YearMonth localDateTimeToYearMonth(LocalDateTime localDateTime) {
        if(localDateTime == null){return null;}
        return YearMonth.of(localDateTime.getYear(),localDateTime.getMonth());
    }

    public static YearMonth localDateToYearMonth(LocalDate localDate){
        if(localDate == null){return null;}
        return YearMonth.of(localDate.getYear(),localDate.getMonth());
    }

    public static LocalDate parseLocalDate(final String str) throws ParseException {
        LocalDateTime localDateTime = parseLocalDateTime(str);
        return localDateTime.toLocalDate();
    }


    public static LocalDateTime toLocalDateTime(long time){
        Instant instant = Instant.ofEpochMilli(time);
        LocalDateTime localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDate;
    }

    public static LocalTime parseLocalTime(final String str) throws ParseException{
        LocalDateTime localDateTime = parseLocalDateTime(str);
        return localDateTime.toLocalTime();
    }

    public static Date parseDate(final String str, final String... parsePatterns) throws ParseException {

        if (NumberUtil.isNumber(str)) {
            return new Date(Long.parseLong(str));
        }

        SimpleDateFormat parser = new SimpleDateFormat();
        final ParsePosition pos = new ParsePosition(0);
        for (final String parsePattern : parsePatterns) {
            parser.applyPattern(parsePattern);
            pos.setIndex(0);
            String str2 = str;
            final Date date = parser.parse(str2, pos);
            if (date != null && pos.getIndex() == str2.length()) {
                return date;
            }
        }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDateTime localDateTime, ZoneId zoneId) {
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    public static YearMonth parseYearMonth(String source) throws ParseException {
        LocalDateTime localDateTime = parseLocalDateTime(source);
        return YearMonth.of(localDateTime.getYear(), localDateTime.getMonth());
    }

    public static MonthDay parseMonthDay(String source){
        if(StrUtil.isBlank(source)){
            return null;
        }
        String[] split = source.split("-");
        return MonthDay.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public static Year parseYear(String source){
        return Year.of(Integer.parseInt(source));
    }

    public static LocalDate firstDayOfMonth(YearMonth yearMonth) {
        LocalDate localDate = LocalDate.of(yearMonth.getYear(),yearMonth.getMonth(),1);
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate lastDayOfMonth(YearMonth yearMonth) {
        LocalDate localDate = LocalDate.of(yearMonth.getYear(),yearMonth.getMonth(),1);
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateUtil.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateUtil.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
}
