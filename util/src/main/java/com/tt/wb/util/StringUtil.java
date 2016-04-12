package com.tt.wb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 作者 peng.tu
 * @version 创建时间：2015年10月20日
 */
public class StringUtil {

	/**
	 * 判断字符串是否是数字
	 * 
	 * @param str
	 *            源字符串
	 * @return boolean
	 */
	public static boolean isNumber(String str) {
		if(!StringUtil.isEmpty(str)) {
			Pattern pattern = Pattern.compile("[0-9]+");
			Matcher isNum = pattern.matcher(str);
			if (isNum.matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符是否为数字
	 * 
	 * @param letter
	 * @return
	 */
	public static boolean isNumber(char letter) {
		if (letter <= '9' && letter >= '0') {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str.trim()) || "null".equalsIgnoreCase(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串转为Long类型
	 * 
	 * @param str
	 * @return
	 */
	public static Long String2Long(String str) {

		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断字符串数组是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String[] str) {
		if (null == str || str.length < 1) {
			return true;
		}
		return false;
	}

	/**
	 * 首字母变大写
	 * 
	 * @param string
	 * @return
	 */
	public static String first2Up(String string) {
		String temp = string.substring(0, 1);
		return string.replaceFirst(temp, temp.toUpperCase());
	}

	/**
	 * 首字母变小写
	 * 
	 * @param string
	 * @return
	 */
	public static String first2Lower(String string) {
		String temp = string.substring(0, 1);
		return string.replaceFirst(temp, temp.toLowerCase());
	}

	/**
	 * 将输入流转化为string
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static String InputStream2String(InputStream is) throws Exception {
		StringBuilder sb = new StringBuilder();
		if(null != is) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();
		}
		return sb.toString();
	}
	
	/**
	 * 获取最后一个大写字母的位置
	 * @param source
	 * @return	若有大写字母则返回字母在字符串中的位置，否则返回-1
	 */
	public static Integer getLastUpCaseIndex(String source) {
		int len = source.length();
		for(int i = len - 1; i >= 0; i-- ) {
			if(Character.isUpperCase(source.charAt(i))) {
				return i;
			}
		}
		return -1;
	}
	
}
