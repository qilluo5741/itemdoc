package com.spring.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * 消费mq消息
 * @author Administrator
 */
public class MQConsumerListener implements MessageListener{

	public void onMessage(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			String res=textMsg.getText();
			System.out.println("读取的数据"+res);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
