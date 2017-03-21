package org.bear.bookstore.test.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsTopicClientTest {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接  
		Connection connection = null;
		// Session： 一个发送或接收消息的线程  
		Session session;
		// Destination ：消息的目的地;消息发送给谁.  
		Destination destination;
		// 消费者，消息接收者  
		MessageConsumer consumer;
		connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		try {
			// 构造从工厂得到连接对象  
			connection = connectionFactory.createConnection();
			// 启动  
			connection.start();
			// 获取操作连接  
			//这个最好还是有事务
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
			destination = session.createTopic("test-topic");
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					try {
						MqBean bean = (MqBean) ((ObjectMessage)message).getObject();
						System.out.println(bean);
						if (null != message) {
							System.out.println("收到消息" + bean.getName());
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
