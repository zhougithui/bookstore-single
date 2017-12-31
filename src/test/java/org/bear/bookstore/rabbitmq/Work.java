package org.bear.bookstore.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

public class Work
{
	//队列名称
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws java.io.IOException,
			java.lang.InterruptedException, TimeoutException
	{
		//区分不同工作进程的输出
		int hashCode = Work.class.hashCode();
		//创建连接和频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//声明队列
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		System.out.println(hashCode
				+ " [*] Waiting for messages. To exit press CTRL+C");
		DefaultConsumer consumer = new DefaultConsumer(channel);
		// 指定消费队列
		boolean ack = false ; //打开应答机制
		channel.basicConsume(QUEUE_NAME, ack, new DefaultConsumer(channel) {
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
				System.out.println(hashCode + " [x] Received '" + message + "'");
				try {
					doWork(message);
				} catch (InterruptedException e) {
				}
				System.out.println(hashCode + " [x] Done");
				channel.basicAck(deliveryTag, false);
			}
		});
	}
	
	/** 
     * 每个点耗时1s 
     * @param task 
     * @throws InterruptedException 
     */  
    private static void doWork(String task) throws InterruptedException  
    {  
        for (char ch : task.toCharArray())  
        {  
            if (ch == '.')  
                Thread.sleep(1000);  
        }  
    }  
}