package com.spring.util;

import org.apache.commons.lang.RandomStringUtils;

public class TestUtil {
	public static void main(String[] args) {
		System.out.println("��һ��"+MD5Util.Decrypted("123456"));
		String code = RandomStringUtils.randomNumeric(6);
		System.out.println("��λ��֤��"+code);
		String inCode=getRandomChar();getRandomChar();
		System.out.println("��λ������"+inCode);
	}
	//����������
	public static String getRandomChar(){
		String randChar = "";
		for (int i = 0; i < 6; i++) {
			int index = (int) Math.round(Math.random() * 1);
			switch (index) {
			case 0:// ��д�ַ�
				randChar += String
						.valueOf((char) Math.round(Math.random() * 25 + 65));
				break;
			default:// ����
				randChar += String.valueOf(Math.round(Math.random() * 9));
				break;
			}
		}
		return randChar;
	}
}
