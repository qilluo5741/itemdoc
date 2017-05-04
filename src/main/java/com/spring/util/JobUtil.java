package com.spring.util;

import java.net.URLEncoder;
import java.util.Date;
/**
 * 任务调度客户端工具
 */
public class JobUtil {
	private static String url;// 请求任务地址
	private static String token;// 安全验证
	private static String backUrl;// 回调地址

	/***
	 * 添加任务
	 * 
	 * @param jobname
	 *            任务名称（确保唯一性）
	 * @param seconds
	 *            计时时间（单位秒） 最低不能超过1秒
	 * @param backUri
	 *            回调方法
	 * @param ct
	 *            回调请求中能得到相应的值
	 * @param errormaxcount
	 *            回调请求中失败请求的次数 为null 默认为一次
	 * @return true 添加任务成功！
	 * @throws Exception
	 * 验证服务器参数是否配置
	 */
	public static boolean addJob(String jobname, Integer seconds,
			String backUri, String ct, Integer errormaxcount,String jobGorupName,Date startData) throws Exception {
		validataInfo();
		// 验证
		if (jobname == null || seconds == null || backUri == null) {
			throw new Exception("任务名、计时时间，回调uri字不能为空!!");
		}
		String httparg = "jobName=" + jobname + "&seconds=" + seconds
				+ "&backUrl="
				+ (URLEncoder.encode(backUrl + "/" + backUri, "utf-8"))
				+ "&context=" + ct+"&token="+MD5Util.GetMD5Code(jobname+token)+"&jobGorupName="+jobGorupName+"&startDate="+startData.getTime();
		
		if(errormaxcount!=null){
			httparg+="&errormaxcount="+errormaxcount;
		}
		System.out.println(httparg);
		String result="";
		try {
			result = HttpUtil.request_post(url+"/addJob.do", httparg).trim();
		} catch (Exception e) {
			throw new Exception("操作异常！");
		}
		// 0: 添加成功
		// 1： 任务已经存在
		// 2：操作异常
		if (result.equals("0")) {
			return true;
		} else if (result.equals("1")) {
			return false;
		} else {
			throw new Exception("操作异常！");
		}
	}

	/**
	 *  移除任务
	 * @param jobName 任务名
	 * @return 0:移除成功  1：任务不存在 2：异常 -1 请求异常 
	 */
	public static int removeJob(String jobName,String jobGorupName){
		String httparg="token="+MD5Util.GetMD5Code(jobName+token)+"&jobName="+jobName+"&jobGorupName="+jobGorupName;
		try {
			String res=HttpUtil.request_post(url+"/removeJob.do", httparg).trim();
			//System.out.println(res);
			return Integer.valueOf(res);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
		
	} 
	// 监控任务
	public static String monitoringJob(String jobGorupName){
		String res;
		try {
			String httparg="jobGroupName="+jobGorupName;
			res = HttpUtil.request_post(url+"/monitoringJob.do", httparg);
			return res;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	private static void validataInfo() throws Exception {
		if (url == null) {
			throw new Exception("（url）is null");
		} else if (token == null) {
			throw new Exception("（token）安全加密不能为null");
		} else if (backUrl == null) {
			throw new Exception("(backUrl)回调地址不能为null");
		}
	}

	private JobUtil() {
	}

	public static void setUrl(String url) {
		JobUtil.url = url;
	}

	public static void setBackUrl(String backUrl) {
		JobUtil.backUrl = backUrl;
	}

	public static void setToken(String token) {
		JobUtil.token = token;
	}
}
