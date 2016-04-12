package com.tt.wb.util;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 作者 peng.tu
 * @version 创建时间：2015年10月20日
 */
public class ObjectUtil {

	/**
	 * 克隆相同类的属性值，目标对象必须继承源对象或者两者为同一类型
	 * 
	 * @param res
	 * @param des
	 */
	public static <T> void copyParam(T res, T des) {

		if (null == res || null == des) {
			return;
		}

		MethodAccess mRes = MethodAccess.get(res.getClass());
		MethodAccess mDes = MethodAccess.get(des.getClass());

		String[] resMethodNames = mRes.getMethodNames();

		for (String methodName : resMethodNames) {

			if (new String(methodName).startsWith("get")) {

				int resInt = mRes.getIndex(methodName);
				methodName = methodName.replaceFirst("get", "set");
				int desInt = mDes.getIndex(methodName);

				mDes.invoke(des, desInt, mRes.invoke(res, resInt));

			}

		}

	}

	/**
	 * 根据类的全名称获取对应类
	 * 
	 * @param name
	 * @return
	 */
	public static Class<? extends Object> getClassByName(String name) {

		Class<?> clss = null;

		try {
			clss = Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clss;
	}

	/**
	 * 获取一个类型所有已定义的字段
	 * 
	 * @param clss
	 * @return
	 */
	public static <T> Field[] getDeclearAllFields(Class<T> clss) {
		return clss.getDeclaredFields();
	}

	/**
	 * 获取一个类型所有已定义public方法名称
	 * 
	 * @param clss
	 * @return
	 */
	public static <T> String[] getAllMethodNames(Class<T> clss) {
		MethodAccess access = MethodAccess.get(clss);
		return access.getMethodNames();
	}

	/**
	 * 获取一个类型所有已定义方法
	 * 
	 * @param clss
	 * @return
	 */
	public static <T> Method[] getAllMethods(Class<T> clss) {
		return clss.getDeclaredMethods();
	}

	/**
	 * 根据方法名调用方法
	 * 
	 * @param t
	 *            调用方法的对象
	 * @param methodName
	 *            方法名称
	 * @param args
	 *            参数
	 * @return 结果
	 * @throws Exception
	 */
	public static <T> Object invoke(T t, String methodName, Object... args) {
		Method method = null;
		try {
			// 当有参数存在时
			if (null != args) {
				// 存放参数的类型
				Class<?>[] clss = new Class<?>[args.length];
				int i = 0;
				for (Object arg : args) {
					clss[i] = arg.getClass();
					i++;
				}
				method = t.getClass().getMethod(methodName, clss);
			} else {
				method = t.getClass().getMethod(methodName);
			}
			if (null != method) {
				return method.invoke(t, args);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据字段获取对应set方法
	 * @param clss	字段和方法所属类
	 * @param field 字段
	 * @return
	 * @throws Exception 
	 */
	public static <T> Method getSetMethod(Class<T> clss, Field field){
		if(null == field) {
			return null;
		}
		String fName = field.getName();
		String methodName = "set" + StringUtil.first2Up(fName);
		Method m = null;
		try {
			m = clss.getMethod(methodName);
		} catch (Exception e) {
		} 
		return m;
	}
	
	/**
	 * 根据字段名称获取对应set方法
	 * @param clss	字段和方法所属类
	 * @param field 字段
	 * @return
	 * @throws Exception 
	 */
	public static <T> Method getSetMethod(Class<T> clss, String fieldName){
		String methodName = "set" + StringUtil.first2Up(fieldName);
		Method m = null;
		try {
			m = clss.getMethod(methodName);
		} catch (Exception e) {
		} 
		return m;
	}
	
	/**
	 * 根据字段获取对应get方法
	 * @param clss	字段和方法所属类
	 * @param field 字段
	 * @return
	 * @throws Exception 
	 */
	public static <T> Method getGetMethod(Class<T> clss, Field field) {
		if(null == field) {
			return null;
		}
		String fName = field.getName();
		String methodName = "get" + StringUtil.first2Up(fName);
		Method m = null;
		try {
			m = clss.getMethod(methodName);
		} catch (Exception e) {
		} 
		return m;
	}
	
	/**
	 * 根据字段名称获取对应get方法
	 * @param clss	字段和方法所属类
	 * @param field 字段
	 * @return
	 * @throws Exception 
	 */
	public static <T> Method getGetMethod(Class<T> clss, String fieldName) {
		String methodName = "get" + StringUtil.first2Up(fieldName);
		Method m = null;
		try {
			m = clss.getMethod(methodName);
		} catch (Exception e) {
		} 
		return m;
	}
	
	/**
	 * 将数组链接，并转化成list
	 * @param bt
	 * @param et
	 * @return
	 */
	public static <T> List<T> connect2List(T[] bt, T[] et) {
		
		List<T> res = new ArrayList<T>();
		
		List<T> bts = Arrays.asList(bt);
		List<T> ets = Arrays.asList(et);
		
		if(null == bts) {
			return ets;
		}
		
		if(null == ets) {
			return bts;
		}
		
		res.addAll(bts);
		res.addAll(ets);
		
		return res;
	}
	
}
