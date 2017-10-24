package com.bjsxt.util;

import org.junit.Test;

public class ServletUtil {
	/**
	 * /work01/servlet/userServlet.action 通过url获取方法名
	 * 
	 * @param url
	 *            请求参数
	 * @return 方法名
	 */
	public static String getMethodName(String url) {
		if (null == url || "".equals(url)) {
			return null;
		}
		int index1 = url.lastIndexOf("/") + 1;
		int index2 = url.lastIndexOf(".");
		if (index2 == -1 || index2 < index1) {
			index2 = url.length();
		}
		
		if (index1 > -1) {
			String methodName = url.substring(index1, index2);
			return methodName;
		} else {
			return null;
		}
	}
}
