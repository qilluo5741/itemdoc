package com.spring.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWraper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWraper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		return clearXss(super.getParameter(name));
	}

	@Override
	public String getHeader(String name) {
		return clearXss(super.getHeader(name));
	}
	@Override
	public String[] getParameterValues(String name) {
		// 得到全部值
		String[] values = super.getParameterValues(name);
		// 重新初始化一个值
		String[] newValues=null;
		try {
			newValues = new String[values.length];
			for (int i = 0; i < values.length; i++) {
				// 重新替换赋值
				newValues[i] = clearXss(values[i]);
			}
		} catch (Exception e) {
			System.out.println("这个错误很正常！");
		}
		return super.getParameterValues(name);
	}

	/***
	 * @param value
	 * @return
	 */
	private String clearXss(String value) {
		if (value == null || "".equals(value)) {
			return value;
		}
		value = value.replaceAll("<","&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","\"\"");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("script", "");
		value = value.replaceAll("update", "");
		value = value.replaceAll("delect", "");
		return value;
	}
}
