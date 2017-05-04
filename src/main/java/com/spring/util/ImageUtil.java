package com.spring.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.RandomStringUtils;

/**
 * 上传图片
 * @author Administrator
 */
public class ImageUtil extends Thread{
	private String baseImageStr;
	private String loadUrl;// 上传地址

	public ImageUtil(String baseImageStr, String loadUrl) {
		super();
		this.baseImageStr = baseImageStr;
		this.loadUrl = loadUrl;
	}

	public void run() {
		HttpUtil.request_post(loadUrl, baseImageStr);
	}
	//创建线程上传云图片
	public static String uploadImage(String base64StrImage,String fileId){
		String fileName=RandomStringUtils.randomNumeric(12);
		//获取上传地址
		String uploadUrl="http://192.168.1.111/itemdoc/upload?fileName="+fileName+"&fileType="+fileId+"&suffix=jpg";
		ImageUtil ui=new ImageUtil(base64StrImage,uploadUrl);
		java.lang.Thread t=new Thread(ui);
		t.start();
		//拼接地址
		String url="http://192.168.1.111/itemdoc/download?fileUri=/"+fileId+"/"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"/"+fileName+".jpg";
		return url;
	}
	//创建线程上传云图片
	public static String uploadImages(String base64StrImage,String fileId){
		String fileName=RandomStringUtils.randomNumeric(12);
		String urlEn=null;
		try {
			urlEn=URLEncoder.encode(fileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//获取上传地址
		String uploadUrl="http://localhost:8080/itemdoc/upload?fileName="+urlEn+"&fileType="+fileId+"&suffix=jpg";
		ImageUtil ui=new ImageUtil(base64StrImage, uploadUrl);
		java.lang.Thread t=new Thread(ui);
		t.start();
		//拼接地址
		String url="http://localhost:8080/itemdoc/download?fileUri=/"+fileId+"/"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"/"+fileName+".jpg";
		return url;
	}
}
