package com.jkoss.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体类与Map互转工具类
 * 
 * @Author Jason
 * @Version 1.0, 2017年9月8日
 * @See
 * @Since com.jkoss.example
 * @Description: TODO
 */
public class BeanToMapUtil {

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static Map toMap(Object bean) {
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		try {
			if (!CommonUtil.isBlank(bean)) {
				Class<? extends Object> clazz = bean.getClass();
				BeanInfo beanInfo = null;

				beanInfo = Introspector.getBeanInfo(clazz);
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (int i = 0; i < propertyDescriptors.length; i++) {
					PropertyDescriptor descriptor = propertyDescriptors[i];
					String propertyName = descriptor.getName();
					if (!propertyName.equals("class")) {
						Method readMethod = descriptor.getReadMethod();
						Object result = null;
						result = readMethod.invoke(bean, new Object[0]);
						if (null != propertyName) {
							propertyName = propertyName.toString();
						}
						if (null != result) {
							result = result.toString();
						}
						returnMap.put(propertyName, result);
					}
				}
			}

		} catch (IntrospectionException e) {
			System.out.println("分析类属性失败");
		} catch (IllegalAccessException e) {
			System.out.println("实例化 JavaBean 失败");
		} catch (IllegalArgumentException e) {
			System.out.println("映射错误");
		} catch (InvocationTargetException e) {
			System.out.println("调用属性的 setter 方法失败");
		}
		return returnMap;
	}

	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param clazz
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return
	 */
	public static <T> T toBean(Class<T> clazz, Map map) {
		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取出bean里的所有方法
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			// 取方法名
			String method = methods[i].getName();
			// 取出方法的类型
			Class[] cc = methods[i].getParameterTypes();
			if (cc.length != 1)
				continue;
			// 如果方法名没有以set开头的则退出本次for
			if (method.indexOf("set") < 0)
				continue;
			// 类型
			String type = cc[0].getSimpleName();
			try {
				// 转成小写
				Object value = method.substring(3, 4).toLowerCase() + method.substring(4);
				// 如果map里有该key
				if (map.containsKey(value) && map.get(value) != null) {
					// 调用其底层方法
					setValue(type, map.get(value), i, methods, obj);
				}
			} catch (IllegalArgumentException e) {
				System.out.println("映射错误");
			}
		}
		return (T) obj;
	}

	/***************************************************************************
	 * 调用底层方法设置值
	 */
	private static void setValue(String type, Object value, int i, Method[] method, Object bean) {
		if (value != null) {
			try {
				if (type.equals("String")) {
					// 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
					method[i].invoke(bean, new Object[] { value });
				} else if (type.equals("int") || type.equals("Integer")) {
					method[i].invoke(bean, new Object[] { new Integer("" + value) });
				} else if (type.equals("double") || type.equals("Double")) {
					method[i].invoke(bean, new Object[] { new Double("" + value) });
				} else if (type.equals("float") || type.equals("Float")) {
					method[i].invoke(bean, new Object[] { new Float("" + value) });
				} else if (type.equals("long") || type.equals("Long")) {
					method[i].invoke(bean, new Object[] { new Long("" + value) });
				} else if (type.equals("boolean") || type.equals("Boolean")) {
					method[i].invoke(bean, new Object[] { Boolean.valueOf("" + value) });
				} else if (type.equals("BigDecimal")) {
					method[i].invoke(bean, new Object[] { new BigDecimal("" + value) });
				} else if (type.equals("Date")) {
					Date date = null;
					if (value.getClass().getName().equals("java.util.Date")) {
						date = (Date) value;
					} else {
						String format = ((String) value).indexOf(":") > 0 ? "yyyy-MM-dd hh:mm:ss" : "yyyy-MM-dd";
						SimpleDateFormat sf = new SimpleDateFormat();
						sf.applyPattern(format);
						date = sf.parse((String) (value));
					}
					if (date != null) {
						method[i].invoke(bean, new Object[] { date });
					}
				} else if (type.equals("byte[]")) {
					method[i].invoke(bean, new Object[] { new String(value + "").getBytes() });
				}
			} catch (Exception e) {
				System.out.println("将linkHashMap 或 HashTable 里的值填充到javabean时出错,请检查!");
				e.printStackTrace();
			}
		}
	}

}