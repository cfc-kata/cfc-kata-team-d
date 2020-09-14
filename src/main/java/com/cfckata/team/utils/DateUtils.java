package com.cfckata.team.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static Logger log = LoggerFactory.getLogger(DateUtils.class);
    /**
     * Number of milliseconds in a standard second.
     */
    public static final long MILLIS_PER_SECOND = 1000;

    /**
     * Number of milliseconds in a standard minute.
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    /**
     * Number of milliseconds in a standard hour.
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    /**
     * Number of milliseconds in a standard day.
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    private static final int[] MONTH_LENGTH = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /** 以T分隔日期和时间，并带时区信息，符合ISO8601规范*/
    public static final String PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
    public static final String PATTERN_ISO_ON_SECOND = "yyyy-MM-dd'T'HH:mm:ssZZ";
    public static final String PATTERN_ISO_ON_DATE = "yyyy-MM-dd";
    public static final String PATTERN_ISO_ON_MONTH = "yyyy-MM";

    /*** 以空格分隔日期和时间，不带时区信息*/
    public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String PATTERN_DEFAULT_ON_SECOND = "yyyy-MM-dd HH:mm:ss";

    /**无分隔符的14位日期格式*/
    public static final String PATTERN_NO_SEP_ON_SECOND = "yyyyMMddHHmmss";
    /**无分隔符的8位日期格式*/
    public static final String PATTERN_NO_SEP_ON_DAY = "yyyyMMdd";

    public static final String PATTERN_NO_SEP_ON_MONTH= "yyyyMM";


    //////// 日期比较 ///////////
    /**
     * 是否是同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(final Date date1, final Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 是否同一时刻.
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameTime(final Date date1, final Date date2) {
        // date.getMillisOf() 比date.getTime()快
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断日期是否在范围内，包含相等的日期
     *
     * @param date
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetween(final Date date, final Date start, final Date end) {
        if (date == null || start == null || end == null || start.after(end)) {
            throw new IllegalArgumentException("some date parameters is null or dateBein after dateEnd");
        }
        return !date.before(start) && !date.after(end);
    }

    ///// 获取日期的//////
    /**
     * 获得日期是一周的第几天, 返回值为1是Sunday , 2是Monday....
     * 可通过Canendar的setFirstDayOfWeek()来改变Monday开始为1
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(final Date date) {
        return get(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得日期是一年的第几天，返回值从1开始
     *
     * @param date
     * @return
     */
    public static int getDayOfYear(final Date date) {
        return get(date, Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得日期是一月的第几周，返回值从1开始. 开始的一周，只要有一天在那个月里都算.
     *
     * @param date
     * @return
     */
    public static int getWeekOfMonth(final Date date) {
        return get(date, Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获得日期是一年的第几周，返回值从1开始. 开始的一周，只要有一天在那一年里都算.
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(final Date date) {
        return get(date, Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得当前日期是一年的第几周，返回值从1开始. 开始的一周，只要有一天在那一年里都算.
     * @return
     */
    public static int getCurWeekOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周的第一天，不设置默认周日为一周的第一天(美国)
        cal.setTimeInMillis(System.currentTimeMillis());
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得当前月份(数字)
     * @return
     */
    public static int getCurMonthOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        //因cal.get(Calendar.MONTH)返回值范围为0~11，比实际月份小1，所以要加1
        return cal.get(Calendar.MONTH)+1;
    }

    /**
     * get
     *
     * @param date
     * @param field
     * @return
     */
    private static int get(final Date date, int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(field);
    }

    ////// 闰年及每月天数///////
    /**
     * 是否闰年.
     *
     * @param date
     * @return
     */
    public static boolean isLeapYear(final Date date) {
        return isLeapYear(get(date, Calendar.YEAR));
    }

    /**
     * 是否闰年，移植Jodd Core的TimeUtil
     *
     * @param y 公元计数, 如2016
     * @return true or false
     */
    public static boolean isLeapYear(int y) {
        boolean result = false;

        if (((y % 4) == 0) && // must be divisible by 4...
                ((y < 1582) || // and either before reform year...
                        ((y % 100) != 0) || // or not a century...
                        ((y % 400) == 0))) { // or a multiple of 400...
            result = true; // for leap year.
        }
        return result;
    }

    /**
     * 获取某个月有多少天, 考虑闰年等因数, 移植Jodd Core的TimeUtil
     *
     * @param date 日期 主要取年和月
     * @return int 天数
     */
    public static int getMonthLength(final Date date) {
        int year = get(date, Calendar.YEAR);
        int month = get(date, Calendar.MONTH);
        return getMonthLength(year, month);
    }

    /**
     * 获取某个月有多少天, 考虑闰年等因数, 移植Jodd Core的TimeUtil
     *
     * @param year 年
     * @param month 月
     * @return int 天数
     */
    public static int getMonthLength(int year, int month) {

        if ((month < 1) || (month > 12)) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }

        return MONTH_LENGTH[month];
    }

    public static Timestamp getCurTimestamp() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }


    /**
     * 得到当前时间的日期  YYYYMMDD
     * 创 建 人:  wenc
     * 创建时间:  2014年6月23日 下午5:54:28
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getCurDT(){
        return getCurTime(PATTERN_NO_SEP_ON_DAY);
    }

    /**
     * 得到当前时间 格式  HHMMSS
     * 创 建 人:  wenc
     * 创建时间:  2014年6月23日 下午5:55:10
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getCurTM(){
        return getCurTime("HHmmss");
    }

    /**
     * 得到当前时间 格式  yyyyMM
     * @return
     */
    public static String getCurMonth(){
        return getCurTime(PATTERN_NO_SEP_ON_MONTH);
    }
    /**
     * 当前时间
     * 创 建 人:  wenc
     * 创建时间:  2014年6月26日 下午2:51:54
     * @param format
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getCurTime(String format) {
        StringBuilder str = new StringBuilder();
        Date ca = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        str.append(sdf.format(ca)) ;
        return str.toString() ;
    }


    /**
     * <功能详细描述>
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2018年4月5日 下午9:50:49
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getCurDate() {
        return new Date();
    }

    public static java.sql.Date getCurSqlDate() {
       java.util.Date date =getCurDate();
       return new java.sql.Date(date.getTime());
    }

    /**
     * 获取当前日期
     * @return yyyy-MM-dd
     */
    public static Date getCurYMD() {
        SimpleDateFormat sdf=new SimpleDateFormat(PATTERN_ISO_ON_DATE);
        Date date=new Date();
        String dateStr= sdf.format(date);
        return stringToDate(dateStr);
    }

    public static java.sql.Timestamp getCurYMDSql() {
        Date date = getCurYMD();
        if(date ==null){
            return null;
        }
        return  new java.sql.Timestamp(date.getTime());
    }

    public static java.sql.Timestamp getCurYMDSql(Date date) {
        if(date ==null){
            return null;
        }
        return new java.sql.Timestamp(date.getTime());
    }

    /**
     * 将yyyyMMdd格式字符串日期转换成日期对象
     * @param date
     * @return yyyyMMdd
     */
    public static Date getCurYYMMDD(String date) {
        SimpleDateFormat sdf=new SimpleDateFormat(PATTERN_NO_SEP_ON_DAY);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            log.error("异常",e);
        }
        return null;
    }

    public static java.sql.Date getCurSqlYMD() {
        try {
            java.util.Date date =getCurYMD();
            if(date==null){
                return null;
            }
            return new java.sql.Date(date.getTime());
        }catch (Exception ex) {
            log.error("转换异常",ex);
        }
        return null;

    }

    /**
     * 获取指定日期
     * @param date
     * @return yyyy-MM-dd
     */
    public static Date getCurYMD(Date date) {
        SimpleDateFormat sdf=new SimpleDateFormat(PATTERN_ISO_ON_DATE);
        String dateStr= sdf.format(date);
        return stringToDate(dateStr);
    }

    /**
     * 获取指定日期
     * @param date
     * @return yyyy-MM-dd HH:mm
     */
    public static Date getCurYMDHM(Date date) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr= sdf.format(date);
        return stringToDate(dateStr);
    }

    /**
     * 获取指定日期
     * @param date
     * @return yyyy-MM-dd HH:00
     */
    public static Date getCurYMDH(Date date) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:00");
        String dateStr= sdf.format(date);
        return stringToDate(dateStr);
    }

    /**
     * 获取系统时间字符串，格式"yyyy-MM-dd HH:mm:ss"
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年7月14日 下午1:50:53
     * @return yyyy-MM-dd HH:mm:ss
     * @see [类、类#方法、类#成员]
     */
    public static String getSysOptDate() {
        Calendar date = Calendar.getInstance();
        Date sysDate = date.getTime();
        return DateUtils.dateToString(sysDate, PATTERN_DEFAULT_ON_SECOND);
    }

    /**
     * 获取传入时间的字符串，格式"yyyy-MM-dd HH:mm:ss"
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getOptDate(Date date) {
        return  DateUtils.dateToString(date, PATTERN_DEFAULT_ON_SECOND);
    }
    /**
     * 根据传入格式返回当前时间的字符串
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年8月19日 上午9:08:08
     * @param strFormat
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getSysOptDate(String strFormat) {
        Calendar date = Calendar.getInstance();
        Date sysDate = date.getTime();
        return  DateUtils.dateToString(sysDate, strFormat);
    }

    /**
     * 根据传入日期和格式获得日期的字符串
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年7月14日 下午1:51:17
     * @param dteValue
     * @param strFormat
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String dateToString(Date dteValue, String strFormat) {
        if (StringUtils.isEmpty(dteValue)) {
            return null;
        }
        SimpleDateFormat clsFormat = new SimpleDateFormat(strFormat);
        return clsFormat.format(dteValue);
    }

    /**
     * 日期String转Date
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年7月29日 下午6:04:26
     * @param strValue
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date stringToDate(String strValue) {
        if (strValue==null) {
            return null;
        }
        SimpleDateFormat clsFormat = null;
        if (strValue.length() > 19)
            strValue = strValue.substring(0, 19);
        if (strValue.length() == 19)
            clsFormat = new SimpleDateFormat(PATTERN_DEFAULT_ON_SECOND);
        else if (strValue.length() == 16)
            clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        else if (strValue.length() == 13)
            clsFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        else if (strValue.length() == 10)
            clsFormat = new SimpleDateFormat(PATTERN_ISO_ON_DATE);
        else if (strValue.length() == 14)
            clsFormat = new SimpleDateFormat(PATTERN_NO_SEP_ON_SECOND);
        else if (strValue.length() == 8) {
            clsFormat = new SimpleDateFormat(PATTERN_NO_SEP_ON_DAY);
        }else{
            clsFormat = new SimpleDateFormat(PATTERN_DEFAULT_ON_SECOND);
        }
        ParsePosition pos = new ParsePosition(0);
        return clsFormat.parse(strValue, pos);
    }


    /**
     * 是否跨月
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2016年9月1日 上午11:31:21
     * @param startAdt
     * @param endAdt
     * @return true:跨月，false：不跨月
     * @see [类、类#方法、类#成员]
     */
    public static boolean kuaYue(String startAdt,String endAdt){
        boolean his = false;//是否跨月
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.stringToDate(startAdt));
            cal.set(Calendar.DAY_OF_MONTH, 1);

            Calendar endCal = Calendar.getInstance();
            endCal.setTime(DateUtils.stringToDate(endAdt));
            endCal.set(Calendar.DAY_OF_MONTH, 1);

            SimpleDateFormat format = new SimpleDateFormat(PATTERN_NO_SEP_ON_MONTH);
            String startMonth=format.format(cal.getTime());
            String endMonth=format.format(endCal.getTime());

            his=!startMonth.equals(endMonth);
        } catch (Exception e) {
            log.error("异常",e);
        }
        return his;
    }

    /**
     * 返回规定时间格式类型字符串 YYYY-MM-DD
     * @param date (为null则默认取当前系统时间)
     * @return YYYY-MM-DD
     */
    public static String getCurDate(Date date) {
        if(date == null){
            date=new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_ISO_ON_DATE);
        return sdf.format(date);
    }

    /**
     * 返回规定时间格式类型字符串 YYYY-MM-DD
     * @param date
     * @return YYYY-MM-DD
     */
    public static String getCurDateForNull(Date date) {
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_ISO_ON_DATE);
        return sdf.format(date);
    }

    /**
     * 返回 YYYY-MM-DD hh:mm:ss
     * @param date (为null则默认取当前时间)
     * @return YYYY-MM-DD hh:mm:ss
     */
    public static String getCurTime(Date date) {
        if(date == null){
            date=new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DEFAULT_ON_SECOND);
        return sdf.format(date);
    }

    /**
     * 获取HH:mm格式时间
     * @param date (为null则默认取当前时间)
     * @return HH:mm
     */
    public static String getHHMM(Date date){
        if(date == null){
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    /**
     * 获取HH:mm:ss格式时间
     * @param date (为null则默认取当前时间)
     * @return HH:mm:ss
     */
    public static String getHHMMSS(Date date){
        if(date == null){
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 在给定的日期时间、单位上增加长度
     * @param date 给定日期时间
     * @param unit 单位 01 小时 02天 03 周 04月 05分钟
     * @param length
     * @return
     */
    public static Date addTime(Date date, String unit, int length){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if("01".equals(unit)){//小时
            calendar.add(Calendar.HOUR_OF_DAY, length);
        }else if("02".equals(unit)){//天
            calendar.add(Calendar.DAY_OF_MONTH, length);
        }else if("03".equals(unit)){//周
            calendar.add(Calendar.WEEK_OF_MONTH, length);
        }else if("04".equals(unit)){//月
            calendar.add(Calendar.MONTH, length);
        }else if("05".equals(unit)){//分钟
            calendar.add(Calendar.MINUTE, length);
        }
        return calendar.getTime();
    }

    /**
     * 在当前日期时间、单位上增加长度
     * @param unit 单位 01 小时 02天 03 周 04月 05分钟
     * @param length
     * @return
     */
    public static Date addTime(String unit, int length){
        Date date = new Date();
        return  addTime(date,unit,length);
    }

    /**
     * 根据周期获取时间
     * @param costCyc 统计周期 01-月   02-季度     03-年
     * @return yyyyMMdd
     */
    public static String getCostCyc(String costCyc){
        Date date = new Date();
        String dateStr = "";
        if("01".equals(costCyc)){//按月
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_NO_SEP_ON_MONTH);
            dateStr = sdf.format(date) + "01";
        }else if("02".equals(costCyc)){//按照季度
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH);//获取月 从0开始
            int year = calendar.get(Calendar.YEAR);//获取年份
            if(month <= 2){//当前月是第一季度
                dateStr = year + "0101";
            }else if(month <= 5){
                dateStr = year + "0401";
            }else if(month <= 8){
                dateStr = year + "0701";
            }else{
                dateStr = year + "1001";
            }
        }else if("03".equals(costCyc)){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);//获取年份
            dateStr = year + "0101";
        }
        return dateStr;
    }

    /**
     * 根据周期获取时间
     * @param costCyc 统计周期 01-月 02-季度 03-年
     * @return yyyy-MM-dd
     */
    public static String getCostCycDate(String costCyc){
        Date date = new Date();
        String dateStr = "";
        if("01".equals(costCyc)){//按月
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_ISO_ON_MONTH);
            dateStr = sdf.format(date) + "-01";
        }else if("02".equals(costCyc)){//按照季度
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH);//获取月 从0开始
            int year = calendar.get(Calendar.YEAR);//获取年份
            if(month <= 2){//当前月是第一季度
                dateStr = year + "-01-01";
            }else if(month <= 5){
                dateStr = year + "-04-01";
            }else if(month <= 8){
                dateStr = year + "-07-01";
            }else{
                dateStr = year + "-10-01";
            }
        }else if("03".equals(costCyc)){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);//获取年份
            dateStr = year + "-01-01";
        }
        return dateStr;
    }

    /**
     * 获取指定日期几分钟/几小时/几天之前的日期
     * @param date 日期
     * @param succCycLong 长度
     * @param succCyc 01：分钟  02：小时  03：天
     * @return
     */
    public static Date getSuccCyc(Date date, BigDecimal succCycLong, String succCyc){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int num = 0 - succCycLong.intValue();
        if("01".equals(succCyc)){//分钟
            calendar.add(Calendar.MINUTE, num);
        }else if("02".equals(succCyc)){//小时
            calendar.add(Calendar.HOUR_OF_DAY, num);
        }else if("03".equals(succCyc)){//天
            calendar.add(Calendar.DAY_OF_MONTH, num);
        }
        return calendar.getTime();
    }

    /**
     * 获取开始时间和结束时间之间隔了多少时间，返回格式：*天*小时*分钟*秒*毫秒
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    public static String interval(Date beginDate, Date endDate) {
        return changeDateTimeToDHMS(endDate.getTime() - beginDate.getTime());
    }


    /**
     *
     * 将毫秒转为 *天*小时*分钟*秒*毫秒
     *
     * @author
     * @date 2019年9月30日 下午2:14:38
     * @param ms
     * @return
     */
    public static String changeDateTimeToDHMS(long ms) {

        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }
        return sb.toString();
    }


}
