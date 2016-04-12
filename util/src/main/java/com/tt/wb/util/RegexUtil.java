package com.tt.wb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 作者 peng.tu
 * @version 创建时间：2015年11月19日
 */
public class RegexUtil {

	/**
	 * 根据正则匹配字符串
	 * @param source	需要匹配的字符串
	 * @param pattern	匹配规则
	 * @return			符合匹配规则的字符串列表
	 */
	public static List<String> macher(String source, String pattern) {
		
		List<String> result=new ArrayList<String>();
		if(!StringUtil.isEmpty(source)) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(source);
			while(m.find()){
				result.add(m.group());
			}
		}
		return result;
	}
	
	/**
	 * 判断是否匹配
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static boolean isMacher(String source, String pattern) {
		if(!StringUtil.isEmpty(source)) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(source);
			while(m.find()){
				return true;
			}
		}
		return false;
	}
	
}
