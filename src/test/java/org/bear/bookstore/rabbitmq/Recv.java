package org.bear.bookstore.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

public class Recv
{
	//队列名称
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws java.io.IOException,
			java.lang.InterruptedException, TimeoutException
	{
		//打开连接和创建频道，与发送端一样
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		//创建队列消费者
		Consumer consumer = new DefaultConsumer(channel);
		//指定消费队列
		channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag,
									   Envelope envelope,
									   AMQP.BasicProperties properties,
									   byte[] body)
					throws IOException
			{
				String routingKey = envelope.getRoutingKey();
				String contentType = properties.getContentType();
				long deliveryTag = envelope.getDeliveryTag();
				String message = envelope.getExchange();
				// (process the message components here ...)
				System.out.println(" [x] Received '" + message + "'");
				channel.basicAck(deliveryTag, false);
			}
		});

	}
}
