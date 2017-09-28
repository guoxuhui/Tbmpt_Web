package com.crfeb.tbmpt.commons.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 通用日期类
 * @author：smxg
 * @date：2016-10-18 10:12
 */
import org.apache.commons.lang.StringUtils;

/**
 * 日期Util类
 * 
 * @author calvin
 */
public class DateUtils {
	private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 */
	public static String format(Date date) {
		return date == null ? " " : format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? " " : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate, getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	public static String getLastDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		// cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
	}

	public static Date getDate(String year, String month, String day) throws ParseException {
		String result = year + "- " + (month.length() == 1 ? ("0 " + month) : month) + "- "
				+ (day.length() == 1 ? ("0 " + day) : day);
		return parse(result);
	}
	
	/**
	 * 方法说明：获取当前年度
	 * @return 返回当前年度
	 * @author:YangYj
	 * @Time: 2017年1月9日 下午8:14:32
	 */
	public static String getCurrentYear(){
		Calendar a=Calendar.getInstance();
		return String.valueOf(a.get(Calendar.YEAR));
	}
	/**
	 * 方法说明：获取当前月份
	 * @return 返回当前月份
	 * @author:YangYj 
	 * @Time: 2017年1月10日 上午8:30:47
	 */
	public static String getCurrentMonth(){
		Calendar a=Calendar.getInstance();
		return String.valueOf(a.get(Calendar.MONTH)+1);
	}
	
	/**
	 * 方法说明：获取当天0点时间
	 * @return 返回当前0点时间字符串
	 * @author:YangYj
	 * @Time: 2017年1月10日 下午4:07:26
	 */
	public static String getTodayStartTime(){
		long current=System.currentTimeMillis();//当前时间毫秒数  
		long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数  
		return String.valueOf(new Timestamp(zero));
		
	}
	/**
	 * 方法说明：获取今天23点59分59秒的时间字符串
	 * @return 返回今天23点59分59秒的时间字符串
	 * @author:YangYj
	 * @Time: 2017年1月10日 下午4:07:52
	 */
	public static String getTodayLastTime(){
		long current=System.currentTimeMillis();//当前时间毫秒数  
		long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数  
		long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数  
		return String.valueOf(new Timestamp(twelve));
		
	}
	
	/**
	 * 方法说明：当前日期加天数
	 * @param d
	 * @return 返回新的天数
	 * @author:YangYj
	 * @Time: 2017年1月10日 下午6:43:29
	 */
	public static String addDayOfCurrentDay(int d){
		String result = "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, d);
        result = sf.format(c.getTime());
        return result;
	}
	
	/**
	 * 方法说明：获取指定时间的所在周
	 * @param date 指定的日期，若为空则默认为当前时间
	 * @return 返回 年份-周次
	 * @author:YangYj
	 * @Time: 2017年1月10日 下午8:22:30
	 */
	public static String getDateWeekNumber(Date date ){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar cl = Calendar.getInstance();
		  if(null != date){
			  cl.setTime(date);
		  }
		  int week = cl.get(Calendar.WEEK_OF_YEAR);
		  int year = cl.get(Calendar.YEAR);
		  String strweek = String.valueOf(week);
		  if(strweek.length()<2){
			  strweek="0"+strweek;
		  }
		  return String.valueOf(year)+"-"+strweek;
	  }
	
	/**
	  * @Title: getMonthFirstDay
	  * @Description: TODO(获取当月第一天)
	  * @author caoyuanzheng
	  * @date：2014-12-18 下午3:31:20
	  * @return String
	 */
	public static String getMonthFirstDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = format.format(c.getTime());
		return getDayAfter(first,0);
	}
	
	/**
	  * @Title: getMonthLastDay
	  * @Description: TODO(获取当月最后一天)
	  * @author caoyuanzheng
	  * @date：2014-12-18 下午3:31:20
	  * @return String
	 */
	public static String getMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH,ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		return last;
	}
	
	private static int weeks = 0;//星期用到
	
	// 获得当前日期与本周一相差的天数
	public static int getMondayPlus() {
       Calendar cd = Calendar.getInstance();
       // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
       int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
       if (dayOfWeek == 1) {
           return -6;
       } else {
           return 2 - dayOfWeek;
       }
   }
	
	// 获得本周星期一的日期
   public static String getCurrentMonday() {
       weeks = 0;
       int mondayPlus = getMondayPlus();
       GregorianCalendar currentDate = new GregorianCalendar();
       currentDate.add(GregorianCalendar.DATE, mondayPlus);
       Date monday = currentDate.getTime();
       DateFormat df = DateFormat.getDateInstance();
       String preMonday = df.format(monday);
       return getDayAfter(preMonday,0);
   }
   
   // 获得相应周的周日的日期
   public static String getSunday() {
       int mondayPlus = getMondayPlus();
       GregorianCalendar currentDate = new GregorianCalendar();
       currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
       Date monday = currentDate.getTime();
       DateFormat df = DateFormat.getDateInstance();
       String preMonday = df.format(monday);
       return preMonday;
   }

	/**
	 * 获得指定日期的后一天
	 *
	 * @param specifiedDay
	 * @return
	 */
	public static String getDayAfter(String specifiedDay,int dayNum) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + dayNum);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}
}