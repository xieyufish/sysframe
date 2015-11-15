package com.shell.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 只包含自己写的一些方法
 * 在真实项目中，可以继承三方库中拥有的字符串工具类，是作用更强大
 * @author shell
 *
 */
public class StringUtil {

	/**
	 * @Title: formatFirstChar
	 * @Description: 格式化第一个字符，大小写。 输入abc ==> Abc
	 * @param string
	 *            原始值, 1：大写，0：小写
	 * @return String
	 * @throws
	 */
	public static String formatFirstChar(String string, int i) {
		String first = string.substring(0, 1);
		String other = string.substring(1, string.length());
		if (i == 1) {
			return first.toUpperCase() + other;
		} else {
			return first.toLowerCase() + other;
		}
	}
	
	public static boolean isBlank(CharSequence cs) {
		int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
	}

	/**
	 * @Title: formatFirstChar
	 * @Description: 格式化第一个字符，大写
	 * @param string
	 * @return String
	 * @throws
	 */
	public static String formatFirstChar(String string) {
		return formatFirstChar(string, 1);
	}

	/**
	 * @Title: join
	 * @Description: 将字符串转换成用逗号隔开的字符串， 默认使用逗号隔开.
	 * @param values
	 * @return String
	 * @throws
	 */
	public static String join(String[] values) {
		return join(values, ",");
	}
	
	/**
	 * 此方法是用于带代替继承父类方法的
	 * 因为现在在构建一个工具集的包，没有引入三方库，所以自己写一个代替一下
	 * 在正式项目中，此工具类可能会继承三方库中的类，所以以后用到要注意
	 * @param values
	 * @param separateor
	 * @return
	 */
	public static String join(Object[] values, String separateor) {
		if (values == null) {
			return null;
		}
		
		if (separateor == null) {
			separateor = " ";
		}
		
		StringBuffer buf = new StringBuffer();
		int length = values.length;
		for (int index = 0; index < length; index++) {
			if (index > 0) {
				buf.append(separateor);
			}
			
			if (values[index] != null) {
				buf.append(values[index]);
			}
		}
		
		return buf.toString();
	}

	/**
	 * @Title: delBracket
	 * @Description: 删除里面的中括号
	 * @param string
	 *            待删的字符串
	 * @return String
	 */
	public static String delBracket(String string) {
		return string.replace("[", "").replace("]", "");
	}

	/**
	 * @Title: col2Field
	 * @Description: 将数据库中的字段改为pojo中的属性名 例： user_name ---> userName
	 * @param
	 * @return String
	 * @throws
	 * @author hewp
	 */
	public static String col2Field(String field) {
		field = field.toLowerCase();
		String[] fields = field.split("_");

		if (fields.length == 1) {
			return field;
		} else {
			for (int i = 1, j = fields.length; i < j; i++) {
				fields[i] = formatFirstChar(fields[i]);
			}
		}
		return join(fields, "");
	}

	/**
	 * @Title: field2Col
	 * @Description: 将pojo属性转换成数据库中的字段(使用小写) 例: userName ---> user_name
	 * @param
	 * @return String
	 * @throws
	 * @author hewp
	 */
	public static String field2Col(String col) {
		StringBuilder string = new StringBuilder();
		for (int i = 0, j = col.length(); i < j; i++) {
			char c = col.charAt(i);
			if (Character.isUpperCase(c)) {
				string.append("_").append(c);
			} else {
				string.append(c);
			}
		}
		return string.toString().toLowerCase();
	}

	/**
	 * @Title: transBean2Map
	 * @Description: 将java转HashMap
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @author hewp
	 */
	public static Map<String, Object> bean2Map(Object obj) {
		if (obj == null) {
			return new HashMap<String, Object>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {

		}
		return map;
	}

	/**
	 * @Title: getPathByDate
	 * @Description: 获取当天的日期，生成目录
	 * @return String
	 * @author hewp
	 */
	public static String getPathByDate() {
		return File.separator
				+ DateUtil.getToday(DateUtil.DateFormatEnum.TIMEF_FORMAT.getValue()).replace("-", File.separator).replace(" ", File.separator)
						.replace(":", File.separator);
	}

}