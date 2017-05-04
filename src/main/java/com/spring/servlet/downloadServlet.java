package com.spring.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
/**
 * �ļ�����
 */
@WebServlet("/download")
public class downloadServlet extends HttpServlet {
	//private static final String rootUrl = "/usr/local/aliyun-oss";
	private static final String rootUrl="E:\\�ҵ��ļ�\\aliyun-oss";
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public downloadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * �ļ��ϴ� post����
	 *//*
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// ���ò��������ʽ
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// �õ�·��
		String fileUri = request.getParameter("fileUri");
		// �õ��������� base64/�� Ĭ��Ϊ��
		String restype = request.getParameter("restype");// ����ֵ��1���ļ����ͣ�ͼƬ����base64
		if (restype != null && restype.equals("base64")) {
			response.setContentType("text/json;charset=utf-8");
			String base64Str=encodeBase64File(rootUrl+fileUri);
			PrintWriter out=response.getWriter();
			out.print("{\"res\":\""+"data:image/png;base64,"+base64Str+"\"}");
			out.flush();
			out.close();
		} else {
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment;filename="+ new String(fileUri.substring(fileUri.lastIndexOf("/") + 1,fileUri.length()).getBytes("GBK"),"ISO8859_1"));
			// ��ȡ�ļ�
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new BufferedInputStream(new FileInputStream(rootUrl+ fileUri));
				// ��������ֽ���
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// ����response�������
				os = new BufferedOutputStream(response.getOutputStream());
				// ����buffer
				byte[] buffer = new byte[4 * 1024]; // 4k Buffer
				int read = 0;
				// ���ļ��ж������ݲ�д������ֽ�����
				while ((read = is.read(buffer)) != -1) {
					baos.write(buffer, 0, read);
				}
				// ������ֽ���д��response���������
				os.write(baos.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// �ر�����ֽ�����response�����
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			}
		}
	}
*/
	/**
	 * ���ļ�ת��base64 �ַ���
	 * @param path�ļ�·��
	 * @return *
	 * @throws Exception
	 */
	public static String encodeBase64File(String path) throws IOException {
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return Base64.encodeBase64String(buffer);
	}/**
	 * �ļ����� post����
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// ���ò��������ʽ
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// �õ�·��
		String fileUri = request.getParameter("fileUri");
		// �õ��������� base64/�� Ĭ��Ϊ��
		String restype = request.getParameter("restype");// ����ֵ��1���ļ����ͣ�ͼƬ����base64
		if (restype != null && restype.equals("base64")) {
			response.setContentType("text/json;charset=utf-8");
			String base64Str=encodeBase64File(rootUrl+fileUri);
			PrintWriter out=response.getWriter();
			out.print("{\"res\":\""+"data:image/png;base64,"+base64Str+"\"}");
			out.flush();
			out.close();
		} else {
			// response.setHeader("Content-Disposition","attachment;filename=\""+fileUri.substring(fileUri.lastIndexOf("/")+1,fileUri.length())+"\"");
			response.setContentType("image/jpeg");
			//response.setContentType("application/octet-stream");
			//response.addHeader("Content-Disposition","attachment;filename="+ new String(fileUri.substring(fileUri.lastIndexOf("/") + 1,fileUri.length()).getBytes("GBK"),"ISO8859_1"));
			//���÷��ص��ļ�����  
			// ��ȡ�ļ�
			InputStream is = null;
			OutputStream os = null;
			try {
				is = createInputStream(fileUri,2);
				// ��������ֽ���
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// ����response�������
				os = new BufferedOutputStream(response.getOutputStream());
				// ����buffer
				byte[] buffer = new byte[4 * 1024]; // 4k Buffer
				int read = 0;
				// ���ļ��ж������ݲ�д������ֽ�����
				while ((read = is.read(buffer)) != -1) {
					baos.write(buffer, 0, read);
				}
				// ������ֽ���д��response���������
				os.write(baos.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// �ر�����ֽ�����response�����
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			}
		}
	}
	/**
	 * ������ȡ��
	 * @param flleUrl
	 * @param errcount
	 * @return
	 */
	private InputStream createInputStream(String flleUrl,int errcount) {
		//��֤�ļ��Ƿ����
		for(int i=0;i<3;i++){
			File file=new File(rootUrl+ flleUrl);
			if(file.exists()){//����
				break;
			}
			try {
				System.out.println("��"+i+"��û���ҵ��ļ���");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			return new BufferedInputStream(new FileInputStream(rootUrl+ flleUrl));
		} catch (FileNotFoundException e) {
			System.out.println("�ļ��Ҳ�����"+e.getMessage());
			return null;
		}
	}
}
