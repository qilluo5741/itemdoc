package com.spring.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.RandomStringUtils;

/**
 * �ϴ�ͼƬ
 * @author Administrator
 */
public class ImageUtil extends Thread{
	private String baseImageStr;
	private String loadUrl;// �ϴ���ַ

	public ImageUtil(String baseImageStr, String loadUrl) {
		super();
		this.baseImageStr = baseImageStr;
		this.loadUrl = loadUrl;
	}

	public void run() {
		HttpUtil.request_post(loadUrl, baseImageStr);
	}
	//�����߳��ϴ���ͼƬ
	public static String uploadImage(String base64StrImage,String fileId){
		String fileName=RandomStringUtils.randomNumeric(12);
		//��ȡ�ϴ���ַ
		String uploadUrl="http://192.168.1.111/itemdoc/upload?fileName="+fileName+"&fileType="+fileId+"&suffix=jpg";
		ImageUtil ui=new ImageUtil(base64StrImage,uploadUrl);
		java.lang.Thread t=new Thread(ui);
		t.start();
		//ƴ�ӵ�ַ
		String url="http://192.168.1.111/itemdoc/download?fileUri=/"+fileId+"/"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"/"+fileName+".jpg";
		return url;
	}
	//�����߳��ϴ���ͼƬ
	public static String uploadImages(String base64StrImage,String fileId){
		String fileName=RandomStringUtils.randomNumeric(12);
		String urlEn=null;
		try {
			urlEn=URLEncoder.encode(fileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//��ȡ�ϴ���ַ
		String uploadUrl="http://localhost:8080/itemdoc/upload?fileName="+urlEn+"&fileType="+fileId+"&suffix=jpg";
		ImageUtil ui=new ImageUtil(base64StrImage, uploadUrl);
		java.lang.Thread t=new Thread(ui);
		t.start();
		//ƴ�ӵ�ַ
		String url="http://localhost:8080/itemdoc/download?fileUri=/"+fileId+"/"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"/"+fileName+".jpg";
		return url;
	}
}
