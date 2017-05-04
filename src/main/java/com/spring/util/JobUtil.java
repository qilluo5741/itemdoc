package com.spring.util;

import java.net.URLEncoder;
import java.util.Date;
/**
 * ������ȿͻ��˹���
 */
public class JobUtil {
	private static String url;// ���������ַ
	private static String token;// ��ȫ��֤
	private static String backUrl;// �ص���ַ

	/***
	 * �������
	 * 
	 * @param jobname
	 *            �������ƣ�ȷ��Ψһ�ԣ�
	 * @param seconds
	 *            ��ʱʱ�䣨��λ�룩 ��Ͳ��ܳ���1��
	 * @param backUri
	 *            �ص�����
	 * @param ct
	 *            �ص��������ܵõ���Ӧ��ֵ
	 * @param errormaxcount
	 *            �ص�������ʧ������Ĵ��� Ϊnull Ĭ��Ϊһ��
	 * @return true �������ɹ���
	 * @throws Exception
	 * ��֤�����������Ƿ�����
	 */
	public static boolean addJob(String jobname, Integer seconds,
			String backUri, String ct, Integer errormaxcount,String jobGorupName,Date startData) throws Exception {
		validataInfo();
		// ��֤
		if (jobname == null || seconds == null || backUri == null) {
			throw new Exception("����������ʱʱ�䣬�ص�uri�ֲ���Ϊ��!!");
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
			throw new Exception("�����쳣��");
		}
		// 0: ��ӳɹ�
		// 1�� �����Ѿ�����
		// 2�������쳣
		if (result.equals("0")) {
			return true;
		} else if (result.equals("1")) {
			return false;
		} else {
			throw new Exception("�����쳣��");
		}
	}

	/**
	 *  �Ƴ�����
	 * @param jobName ������
	 * @return 0:�Ƴ��ɹ�  1�����񲻴��� 2���쳣 -1 �����쳣 
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
	// �������
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
			throw new Exception("��url��is null");
		} else if (token == null) {
			throw new Exception("��token����ȫ���ܲ���Ϊnull");
		} else if (backUrl == null) {
			throw new Exception("(backUrl)�ص���ַ����Ϊnull");
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
