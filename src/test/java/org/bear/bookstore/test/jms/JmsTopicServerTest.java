package org.bear.bookstore.test.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * MQ处理消息的流程决定的：
	消息从生成方客户端传送到消息服务器。
	消息服务器读取消息。
	消息被放置到持久性存储器当中（出于可靠性的考虑）。
	消息服务器确认收到消息（出于可靠性的考虑）。
	消息服务器确定消息的路由。
	消息服务器写出消息。
	消息从消息服务器传送到使用方客户端。
	使用方客户端确认收到消息（出于可靠性的考虑）。
	消息服务器处理客户端确认（出于可靠性的考虑）。
	消息服务器确定已经处理客户端确认。
 * @author q
 *
 */
public class JmsTopicServerTest {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		Connection connection;
		Session session;
		Destination destination;
		MessageProducer producer;
		connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			destination = session.createTopic("test-topic");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//优先级不能影响先进先出
			MqBean bean = new MqBean();
			bean.setAge(13);
			for(int i=0;i<10;i++){
				Thread.sleep(1000);
				bean.setName("小黄"+i);
				producer.send(session.createObjectMessage(bean));
			}
			producer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
