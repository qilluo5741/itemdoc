package com.spring.util;

import org.apache.log4j.Logger;

public class Log {
	//定义一个日志记录器
	private static Logger logger = null;
	/**
	 * 向外部提供产生对象的方法
	 * @return
	 */
	public static Logger getInstance(){
		if(logger == null){
			new Log();
		}
		return logger;
	}
	//私有自身构造方法，避免外部创建
	private Log() {
		logger = Logger.getLogger(this.getClass());
	}
}