package com.shell.util;

import java.awt.SecondaryLoop;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * @ClassName: DateUtil
 * @Description: 完成一些对日期的计算工作
 * @author hewp
 */
@SuppressWarnings("unused")
public class DateUtil {
	public static enum DateFormatEnum{
		/**
		 * @Fields DATE_FORMAT : yyyy-MM-dd
		 */
		DATE_FORMAT("yyyy-MM-dd"),
		/**
		 * @Fields TIMEF_FORMAT : yyyy-MM-dd HH:mm:ss
		 */
		TIMEF_FORMAT("yyyy-MM-dd HH:mm:ss"),
		
		/**
		 * @Fields ZHCN_DATE_FORMAT : yyyy年MM月dd日
		 */
		ZHCN_DATE_FORMAT("yyyy年MM月dd日"),
		
		/**
		 * @Fields ZHCN_TIME_FORMAT : yyyy年MM月dd日HH时mm分ss秒
		 */
		ZHCN_TIME_FORMAT("yyyy年MM月dd日HH时mm分ss秒"),
		
		/**
		 * @Fields DATE_STR_FORMAT : yyyyMMdd
		 */
		DATE_STR_FORMAT("yyyyMMdd"),
		
		/**
		 * @Fields MSEL_TIME_FORMAT : HH:mm:ss
		 */
		MSEL_TIME_FORMAT("HH:mm:ss"),
		
		/**
		 * @Fields TIME_STR_FORMAT : yyyyMMddHHmmss
		 */
		TIME_STR_FORMAT("yyyyMMddHHmmss");
		
		private String value;
		
		DateFormatEnum(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	public static enum TimeType {
		SECOND(Calendar.SECOND),
		MINUTES(Calendar.MINUTE),
		HOUR(Calendar.HOUR),
		DAY(Calendar.DATE),
		MONTH(Calendar.MONTH),
		YEAY(Calendar.YEAR);
		private int value;
		private TimeType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	}
	
	private static DateFormat dateFormat = null;
	private static DateFormat dateTimeFormat = null;
	private static DateFormat zhcnDateFormat = null;
	private static DateFormat zhcnDateTimeFormat = null;
	static {
		dateFormat = new SimpleDateFormat(DateFormatEnum.DATE_FORMAT.value);
		dateTimeFormat = new SimpleDateFormat(DateFormatEnum.TIMEF_FORMAT.value);
		zhcnDateFormat = new SimpleDateFormat(DateFormatEnum.ZHCN_DATE_FORMAT.value);
		zhcnDateTimeFormat = new SimpleDateFormat(DateFormatEnum.ZHCN_TIME_FORMAT.value);
	}

	/**
	 * @Title: getToday
	 * @Description: 获取今天的日期，默认格式如：2015-02-11
	 * @param
	 * @return 返回今天的日期
	 * @throws
	 */
	public static String getToday() {
		return getToday(DateFormatEnum.DATE_FORMAT.value);
	}

	/**
	 * @Title: getToday
	 * @Description: 获取今天的日期，格式自定
	 * @param
	 * @return 返回今天的日期
	 * @throws
	 */
	public static String getToday(String pattern) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * @Title: getInternalTimeByDay
	 * @Description: 得到当前时间的前/后若干天的时间 例如当前时间2015-02-11 间隔天数30天，则返回2015-03-13
	 *               使用默认格式：DATE_FORMAT : yyyy-MM-dd
	 * @param days
	 *            隔的天数
	 * @return String
	 * @throws
	 */
	public static String getInternalTimeByDay(int days) {
		return getInternalTimeByDay(days, DateFormatEnum.DATE_FORMAT.value);
	}

	/**
	 * @Title: getInternalTimeByDay
	 * @Description: 得到当前时间的前/后若干天的时间 例如当前时间2015-02-11 间隔天数30天，则返回2015-03-13
	 * @param days
	 *            隔的天数
	 * @return String
	 * @throws
	 */
	public static String getInternalTimeByDay(int days, String pattern) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getDefault());
		now.add(Calendar.DATE, days);
		return (sdf.format(now.getTime()));
	}

	/**
	 * @Title: getInternalTimeByMonth
	 * @Description: 得到当前时间的前/后若干月的时间 例如当前时间2015-02-11 间隔月数3月，则返回2015-05-11
	 *               默认格式：DATE_FORMAT : yyyy-MM-dd
	 * @param months
	 *            间隔月数
	 * @return String - 返回当时的时间
	 * @throws
	 */
	public static String getInternalTimeByMonth(int months) {
		return getInternalTimeByMonth(months, DateFormatEnum.DATE_FORMAT.value);
	}

	/**
	 * @Title: getInternalTimeByMonth
	 * @Description: 得到当前时间的前/后若干月的时间 例如当前时间2015-02-11 间隔月数3月，则返回2015-05-11
	 * @param months
	 *            间隔月数
	 * @return String - 返回当时的时间
	 * @throws
	 */
	public static String getInternalTimeByMonth(int months, String pattern) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getDefault());
		now.add(Calendar.MONTH, months);
		return (sdf.format(now.getTime()));
	}

	/**
	 * @Description 比较当前日期和指定日期 return boolean 如果当前日期在指定日期之后返回true否则返回flase
	 * @param dateStr
	 *            指定日期
	 * @param pattern
	 *            指定日期的格式
	 * @return boolean
	 */
	public static boolean dateCompare(String dateStr, String pattern) {
		boolean bea = false;
		SimpleDateFormat sdf_d = new SimpleDateFormat(pattern);
		String isDate = sdf_d.format(new java.util.Date());
		java.util.Date date1;
		java.util.Date date0;
		try {
			date1 = sdf_d.parse(dateStr);
			date0 = sdf_d.parse(isDate);
			if (date0.after(date1)) {
				bea = true;
			}
		} catch (ParseException e) {
			bea = false;
		}
		return bea;
	}
	
	/**
	 * @Description 比较指定两日期,如果dateStr1晚于dateStr2则return true;
	 * @param  dateStr1  指定日期
	 * @param  dateStr2  指定日期
	 * @param  pattern  指定日期的格式
	 * @return boolean
	 */
	public static boolean dateCompare(String dateStr1, String dateStr2, String pattern) {
		boolean bea = false;
		SimpleDateFormat sdf_d = new SimpleDateFormat(pattern);
		java.util.Date date1;
		java.util.Date date0;
		try {
			date1 = sdf_d.parse(dateStr1);
			date0 = sdf_d.parse(dateStr2);
			if (date0.after(date1)) {
				bea = true;
			}
		} catch (ParseException e) {
			bea = false;
		}
		return bea;
	}

	/**
	 * 
	 * @return 得到上个月月份 如201505
	 */
	public static String getYesterMonth() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MONTH, -2);
		String DATE_FORMAT = "yyyyMM";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * 
	 * @return 得到本年度年份 如2015
	 */
	public static String getYear() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		// now.add(Calendar.MONTH,-1);
		String DATE_FORMAT = "yyyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * 
	 * @return 得到本月月份 如09
	 */
	public static String getMonth() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MONTH, -1);
		String DATE_FORMAT = "MM";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * 
	 * @param formatStr
	 * @return
	 */
	private static DateFormat getDateFormat(String formatStr) {
		return new SimpleDateFormat(formatStr);
	}

	/**
	 * @Title: getDate
	 * @Description: 按照默认formatStr的格式，转化dateTimeStr为Date类型
	 *               dateTimeStr必须是formatStr的形式
	 * @param
	 * @return Date
	 * @throws
	 */
	public static Date getDate(String dateTimeStr, String formatStr) {
		try {
			if (StringUtil.isBlank(dateTimeStr)) {
				return null;
			}
			DateFormat sdf = getDateFormat(formatStr);
			java.util.Date d = sdf.parse(dateTimeStr);
			return d;
		} catch (ParseException e) {
			// throw new LangException(e);
		}
		return null;
	}

	/**
	 * @Title: getDate
	 * @Description: 按照默认formatStr的格式，转化dateTimeStr为Date类型
	 *               dateTimeStr必须是formatStr的形式
	 * @param
	 * @return Date
	 * @throws
	 */
	public static Date getDate(String dateTimeStr) {
		return getDate(dateTimeStr,DateFormatEnum.DATE_FORMAT.value);
	}
	/**
	 * 将Date转换成formatStr格式的字符串
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToDateString(Date date, String formatStr) {
		DateFormat df = getDateFormat(formatStr);
		return df.format(date);
	}

	/**
	 * 把日期转化为日期字符串
	 * 
	 * @param strDate
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static String date2str(Date date, String dateFormat) {
		String strDate = null;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			strDate = sdf.format(date);
		}
		return strDate;
	}

	/**
	 * 把字符串转化为java.util.Date
	 * 
	 * @param strDate
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static Date str2Date(String strDate, String dateFormat){
		Date date = null;
		try {
			if ((strDate != null) && (strDate != "")) {
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				date = sdf.parse(strDate);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}
	
	/**
	 * 把字符串转化为java.util.Date,默认DATE_FORMAT : yyyy-MM-dd
	 * 
	 * @param strDate
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static Date str2Date(String strDate){
		return str2Date(strDate, DateFormatEnum.DATE_FORMAT.value);
	}
	
	/**
	 * 功能描述：<br>
	 * 对日期进行加减
	 * 
	 * @param date
	 *            基日期
	 * @param type
	 *            加减的时间类型，取本类中的常量
	 * @param counts
	 *            数值，正表示加，负表示减
	 * @return
	 */
	public static Date addOrMinus(Date date, TimeType type, int counts) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type.getValue(), counts);
		return calendar.getTime();
	}

	/**
	 * 功能描述：<br>
	 * 得到传入日期所在星期的第一天(星期日)的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return addOrMinus(date, TimeType.DAY, 1 - dayOfWeek);
	}

	/**
	 * 功能描述:<br>
	 * 得到传入日期所在月的第一天的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		return addOrMinus(date, TimeType.DAY, 1 - dayOfMonth);
	}

	/**
	 * 功能描述:<br>
	 * 得到传入日期所在星期的最后一天(星期六)的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfWeek(Date date) {
		Date firstDateOfWeek = getFirstDateOfWeek(date);
		return addOrMinus(firstDateOfWeek, TimeType.DAY, 6);
	}

	/**
	 * 功能描述:<br>
	 * 得到传入日期所在月份的最后一天的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int actualMax = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return addOrMinus(getFirstDateOfMonth(date), TimeType.DAY, actualMax - 1);
	}

	/**
	 * 功能描述:<br>
	 * 计算两个日期之间的时间差
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static String minusBetweenDate(Date firstDate, Date secondDate) {
		long firstTime = firstDate.getTime();
		long secondTime = secondDate.getTime();
		return millsToDate(firstTime - secondTime);
	}
	
	/**
	 * 友好显示时间  例如：2小时前
	 * @param time
	 * @return
	 */
	public static String friendlyTime(Date time) {
		if (time == null) return "";
		return minusBetweenDate(Calendar.getInstance().getTime(), time) + "前";
	}
	
	/**
	 * 将毫秒值转换为想要的日期格式
	 * @param mills
	 * @param pattern
	 * @return
	 */
	public static String long2DateString(long mills, String pattern) {
		Date date = new Date(mills);
		return date2str(date, pattern);
	}
	
	/**
	 * 将long型毫秒值转换为时间的表示形式
	 * @param mills
	 * @return
	 */
	private static String millsToDate(long mills) {
		mills = mills < 0 ? -mills : mills;
		long aSecond = 1000;
		long aMinute = 60 * aSecond;
		long aHour = 60 * aMinute;
		long aDay = 24 * aHour;
		long aMonth = 30 * aDay;
		
		long month = mills / aMonth;
		long day = (mills % aMonth) / aDay;
		long hour = (mills % aDay) / aHour;
		long minutes = (mills % aHour) / aMinute;
		long second = (mills % aMinute) / aSecond;
		
		StringBuilder sb = new StringBuilder();
		sb.append(month == 0 ? "" : month + "月  ");
		sb.append(day == 0 ? "" : day + "天  ");
		sb.append(hour == 0 ? "" : hour + "时  ");
		sb.append(minutes == 0 ? "" : minutes + "分  ");
		sb.append(second == 0 ? "" : second + "秒");
		
		return sb.toString();
	}
}
