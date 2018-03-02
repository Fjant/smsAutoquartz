package cn.zhuoqin.platform.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 本类是公用方法的集合 对数据进行格式化 处理与时间有关的数据 编码的转换 数据的精度控制 字符串全角与半角之间的转换
 * 
 * @author sinosoft
 * @version 1.0 2009-07-22 新建
 */

public final class DataUtil {

	/**
	 * 默认构造器
	 */
	private DataUtil() {
	}

	/**
	 * 保留小数，保留后的小数位数在0~100范围内
	 * 
	 * @param value
	 *            需要格式化的数字
	 * @param num
	 *            需要保留的小数位数
	 * @return 格式化后的数字
	 * @throws RuntimeException
	 *             如果value不为数字或者num不在0~100范围内，则抛出异常
	 */
	public static String formatPoint(String value, int num) throws RuntimeException {
		double tmpValue = 0;
		try {
			tmpValue = Double.parseDouble(value);
		} catch (Exception e) {
			throw new RuntimeException(value + "不是数字");
		}
		if (num < 0 || num > 100) {
			throw new RuntimeException(num + "不在保留的范围（0~100）内");
		}
		StringBuffer pointStr = new StringBuffer("##0");
		if (num != 0) {
			pointStr.append(".");
		}
		for (int i = 0; i < num; i++) {
			pointStr.append("0");
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat(pointStr.toString());
		return df.format(tmpValue);
	}

	/**
	 * 判断值是否不为空
	 * 
	 * @param value
	 *            被判断的值
	 * @return true-value不为空，false-value为空值
	 */
	public static boolean hasValue(String value) {
		if (value == null || value.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 把日期类型转换成String
	 * 
	 * @param value
	 *            Date型日期
	 * @param type
	 *            转换成String型日期后的格式
	 * @return String型日期
	 */
	public static String convertDateToString(Date value, String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(value);
	}

	/**
	 * 把String转换成日期
	 * 
	 * @param value
	 *            String型日期
	 * @param type
	 *            String型日期的格式
	 * @return Date型日期
	 * @throws ParseException
	 *             日期转换发生错误
	 */
	public static Date convertStringToDate(String value, String type) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.parse(value);
	}

	/**
	 * 获取当前日期时间 参考格式：yyyy年MM月dd日HH时（hh时）mm分ss秒ms毫秒E本月第F个星期
	 * 对应的值：2009年07月22日15时（03时）05分30秒530毫秒星期三本月第4个星期
	 * 
	 * @param type
	 *            日期时间的格式
	 * @return String型日期时间
	 */
	public static String getCurrentDateTime(String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(new Date());
	}

	/**
	 * 日期时间格式转换
	 * 
	 * @param value
	 *            转换前的值
	 * @param srcFormat
	 *            转换前的格式
	 * @param destFormat
	 *            转换后的格式
	 * @return 转换后的值
	 * @throws ParseException
	 *             日期转换发生错误
	 */
	public static String dateFormat(String value, String srcFormat, String destFormat) throws ParseException {
		Date date = convertStringToDate(value, srcFormat);
		return convertDateToString(date, destFormat);
	}

	/**
	 * 计算两个日期的间隔
	 * 
	 * @param type
	 *            间隔的单位：y-年，m-月，d-日，不填默认为日
	 * @param sdate1
	 *            String型日期，格式为yyyy-MM-dd
	 * @param sdate2
	 *            String型日期，格式为yyyy-MM-dd
	 * @return 间隔的数量。如果日期sdate2在日期sdate1之后，则结果为正数；如果日期sdate2在日期sdate1之前，则结果为负数
	 * @throws ParseException
	 *             日期转换发生错误
	 */
	public static int dateDiff(String type, String sdate1, String sdate2) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = df.parse(sdate1);
		Date date2 = df.parse(sdate2);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		if ("y".equalsIgnoreCase(type)) {
			return yearDiff;
		} else if ("m".equalsIgnoreCase(type)) {
			int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
			return monthDiff;
		} else {
			long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
			long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
			int dayDiff = (int) ((ldate2 - ldate1) / (3600000 * 24));
			return dayDiff;
		}
	}

	/**
	 * 计算日期加上一段间隔之后的新日期
	 * 
	 * @param type
	 *            间隔的单位：y-年，m-月，d-日，不填默认为日
	 * @param sdate
	 *            String型日期，格式为yyyy-MM-dd
	 * @param num
	 *            间隔数量
	 * @return 返回新日期，格式为yyyy-MM-dd
	 * @throws ParseException
	 *             日期转换发生错误
	 */
	public static String dateAdd(String type, String sdate, int num) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(sdate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if ("y".equalsIgnoreCase(type)) {
			cal.add(Calendar.YEAR, num);
		} else if ("m".equalsIgnoreCase(type)) {
			cal.add(Calendar.MONTH, num);
		} else {
			cal.add(Calendar.DATE, num);
		}
		return df.format(cal.getTime());
	}

	/**
	 * 计算两个时间相差的秒数
	 * 
	 * @param time1
	 *            String型时间，格式为yyyy-MM-dd HH:mm:ss
	 * @param time2
	 *            String型时间，格式为yyyy-MM-dd HH:mm:ss
	 * @return 相差的秒数。如果时间time2在时间time1之后，则结果为正数；如果时间time2在时间time1之前，则结果为负数
	 * @throws ParseException
	 */
	public static long timeDiff(String time1, String time2) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = df.parse(time1);
		Date date2 = df.parse(time2);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
		long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
		long result = (ldate2 - ldate1) / 1000;
		return result;
	}

	/**
	 * Unicode编码转换成GB2312编码
	 * 
	 * @param src
	 *            Unicode编码的字符串
	 * @return GB2312编码的字符串
	 * @throws UnsupportedEncodingException
	 *             编码转换错误
	 */
	public static String UnicodeToGB(String src) throws UnsupportedEncodingException {
		if (DataUtil.hasValue(src)) {
			return new String(src.getBytes("ISO-8859-1"), "GB2312");
		} else {
			return src;
		}
	}

	/**
	 * GB2312编码转换成Unicode编码
	 * 
	 * @param src
	 *            GB2312编码的字符串
	 * @return Unicode编码的字符串
	 * @throws UnsupportedEncodingException
	 *             编码转换错误
	 */
	public static String GBToUnicode(String src) throws UnsupportedEncodingException {
		if (DataUtil.hasValue(src)) {
			return new String(src.getBytes("GB2312"), "ISO-8859-1");
		} else {
			return src;
		}
	}

	/**
	 * 过滤跨站脚本关键字
	 * 
	 * @param src
	 *            输入的字符串
	 * @return 过滤后的字符串
	 */
	public static String filterStr(String src) {
		if (DataUtil.hasValue(src)) {
			src = src.replaceAll("<", "");
			src = src.replaceAll(">", "");
			src = src.replaceAll("'", "");
			src = src.replaceAll("&", "＆");
			src = src.replaceAll("#", "＃");
			src = src.replaceAll("%", "％");
			src = src.replaceAll("\"", "");
			src = src.trim();
			return src;
		} else {
			return "";
		}
	}

	/**
	 * 半角转全角 半角空格为32，全角空格为12288 其他字符半角(33-126)与全角(65281-65374)的对应关系为：相差65248
	 * 
	 * @param src
	 *            待转换的字符串
	 * @return 转换后的全角字符串
	 */
	public static String toSBC(String src) {
		char[] c = src.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = 12288;
			} else if (c[i] >= 33 && c[i] <= 126) {
				c[i] += 65248;
			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角 半角空格为32，全角空格为12288 其他字符半角(33-126)与全角(65281-65374)的对应关系为：相差65248
	 * 
	 * @param src
	 *            待转换的字符串
	 * @return 转换后的半角字符串
	 */
	public static String toDBC(String src) {
		char[] c = src.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = 32;
			} else if (c[i] >= 65281 && c[i] <= 65374) {
				c[i] -= 65248;
			}
		}
		return new String(c);
	}

	/**
	 * 获取项目所在路径
	 * 
	 * @return 项目路径
	 * @throws Exception
	 *             未找到路径
	 */
	public static String getProjectLocalPath() throws Exception {
		String path = DataUtil.class.getResource("").getFile();
		path = URLDecoder.decode(path, "UTF-8");

		if (path.lastIndexOf("/WEB-INF") >= 0)
			path = path.substring(0, path.lastIndexOf("/WEB-INF"));
		else
			path = path.substring(0, path.lastIndexOf("/classes"));
		String temp = path.substring(0, 5);
		if ("file:".equalsIgnoreCase(temp)) {
			path = path.substring(5);
		}

		return path;
	}

	/**
	 * 读取网页内容
	 * 
	 * @param str
	 *            网页地址
	 * @return str所指向的网页内容
	 * @throws java.io.IOException
	 */
	public static String getHtmlCodeByURL(String str) throws java.io.IOException {
		URL url = new URL(str);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		InputStream in = urlConnection.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int count = -1;
		while (in != null && (count = in.read(data)) != -1) {
			out.write(data, 0, count);
		}
		out.flush();
		String result = out.toString();
		out.close();
		return result;
	}
}