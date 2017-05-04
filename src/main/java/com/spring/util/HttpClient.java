package com.spring.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClient {
	/**
	 * post����
	 * @param url �����ַ
	 * @param paramMap ������� ͨ����ֵ�Ե���ʽ
	 * @return
	 */
	public static String post(String url, Map<String, String> paramMap) {
		// ����Ĭ�ϵ�httpClientʵ��.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// ����httppost
		HttpPost httppost = new HttpPost(url);
		// ������������
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (String key : paramMap.keySet()) {
			formparams.add(new BasicNameValuePair(key, paramMap.get(key)));
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			//System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, "UTF-8");
			} finally {
				response.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			// �ر�����,�ͷ���Դ
			try {
				httpclient.close();
			} catch (IOException e) {
				return null;
			}
		}
	}
	/***
	 * get����
	 * @param url
	 * @return
	 */
	 public static String get(String url) {  
	        CloseableHttpClient httpclient = HttpClients.createDefault();  
	        try {  
	            // ����httpget.    
	            HttpGet httpget = new HttpGet(url);  
	            // ִ��get����.    
	            CloseableHttpResponse response = httpclient.execute(httpget);  
	            try {  
	                // ��ȡ��Ӧʵ��    
	                HttpEntity entity = response.getEntity();  
	                // ��ӡ��Ӧ״̬    
	               // System.out.println(response.getStatusLine());  
	               return EntityUtils.toString(entity);
	            } finally {  
	                response.close();  
	            }
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            // �ر�����,�ͷ���Դ    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	               return null;
	            }  
	        }
			return null;  
	    }  
}
