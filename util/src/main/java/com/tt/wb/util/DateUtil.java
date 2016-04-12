package com.tt.wb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 作者 peng.tu
 * @version 创建时间：2015年10月20日
 */
public class DateUtil {

	/**
	 * 将字符串转化为时间对象
	 * 
	 * @param dateStr
	 *            源字符串的格式
	 * @return
	 */
	public static Date String2Date(String dateStr) {

		// 最终的char数组
		char[] dateChar = new char[128];
		// 临时存放数字
		char[] temp = new char[5];
		// 存放时间格式对应字母
		char[] typeChar = { 'y', 'M', 'd', 'H', 'm', 's' };

		int t = 0;
		int tc = -1;
		int dc = 0;
		//判断是否进入下个类型
		boolean typeChange = true;
		
		// 遍历目标string
		for (int i = 0; i < dateStr.length(); i++) {
			char letter = dateStr.charAt(i);
			// 如果字母是数字
			if (StringUtil.isNumber(letter)) {
				temp[t] = letter;
				//是否进入下个数字
				if(typeChange) {
					tc++;
					typeChange = false;
				}
				t++;
			}
			if (!StringUtil.isNumber(letter) || i == (dateStr.length() - 1)) {
				for (int k = 0; k < temp.length; k++) {
					// 如果为空
					if ('\0' == temp[k]) {
						break;
					}
					
					dateChar[dc] = typeChar[tc];
					dc++;
				}

				if (!StringUtil.isNumber(letter)) {
					dateChar[dc] = letter;
					dc++;
					if(!typeChange) {
						typeChange = true;
					}
				}
				temp = new char[5];
				t = 0;
			}

		}

		String dateType = new String(dateChar);
		dateType = dateType.substring(0, dateType.indexOf("\0"));
		Date date = null;
		//if (DATE_FORMATE_STYLE.contains(dateType.trim())) {
			SimpleDateFormat format = new SimpleDateFormat(dateType.trim());
			try {
				date = format.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		//}
		return date;
	}

	/**
	 * 获取当前年月日时间戳
	 * 
	 * @param dataTime
	 * @return
	 */
	public static Long getNowDayStamp() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dataStr = format.format(new Date());
		return String2Date(dataStr).getTime();
	}

	public static String date2String(Date date, String type) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		return format.format(date);
	}
	
	/**
	 * 获取当前年份
	 * @return
	 */
	public static Integer getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	/**
	 * 获取当前月份
	 * @return
	 */
	public static Integer getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}
}
