package com.spring.config;

import org.springframework.stereotype.Component;

@Component
public class GaodeConfig {
	private static GaodeConfig gaode;

	GaodeConfig() {
		gaode = this;
	}

	public static GaodeConfig newGaodeConfig() {
		return gaode;
	}
	private static String tableid;
	private static String appKey;

	public static String getTableid() {
		return tableid;
	}

	public static void setTableid(String tableid) {
		GaodeConfig.tableid = tableid;
	}

	public static String getAppKey() {
		return appKey;
	}

	public static void setAppKey(String appkey) {
		GaodeConfig.appKey = appkey;
	}
}
