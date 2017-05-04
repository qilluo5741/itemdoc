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
	 * post请求
	 * @param url 请求地址
	 * @param paramMap 请求参数 通过键值对的形式
	 * @return
	 */
	public static String post(String url, Map<String, String> paramMap) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
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
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				return null;
			}
		}
	}
	/***
	 * get请求
	 * @param url
	 * @return
	 */
	 public static String get(String url) {  
	        CloseableHttpClient httpclient = HttpClients.createDefault();  
	        try {  
	            // 创建httpget.    
	            HttpGet httpget = new HttpGet(url);  
	            // 执行get请求.    
	            CloseableHttpResponse response = httpclient.execute(httpget);  
	            try {  
	                // 获取响应实体    
	                HttpEntity entity = response.getEntity();  
	                // 打印响应状态    
	               // System.out.println(response.getStatusLine());  
	               return EntityUtils.toString(entity);
	            } finally {  
	                response.close();  
	            }
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	               return null;
	            }  
	        }
			return null;  
	    }  
}
