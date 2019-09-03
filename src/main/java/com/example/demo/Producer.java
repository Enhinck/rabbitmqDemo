package com.example.demo;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
@RabbitListener
public class Producer {

	public static void main(String[] argv) throws Exception {
		exchange();
	}


	public static void sendAppMessage() throws Exception {
		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		String message = "{\"222\":\"22\"}";
		channel.basicPublish("ex_app_message_d", "sys", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
		connection.close();
	}
	
	public static void topic() throws Exception {
		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		// channel.exchangeDeclare(QUEUE_NAME, false, false, false, null)
		// channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!111";
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}
	

	public static Connection getConnection() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("smart-dev.gtdreamlife.com");
	/*	factory.setUsername("admin");
		factory.setPassword("Greentown@123");*/
		factory.setUsername("guest");
		factory.setPassword("Greentown123");
		Connection connection = factory.newConnection();
		return connection;
	}

	public static void exchange() throws Exception {
		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		// channel.exchangeDeclare(QUEUE_NAME, false, false, false, null)
		// channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "{\"accountMessages\":[{\"unionId\":\"e57c368b8c\",\"projectId\":53,\"projectName\":\"绿城西溪国际\",\"username\":\"名字老厉害了\",\"phone\":\"13588307742\",\"plateNumber\":\"浙ATRRRRT\",\"identity\":\"1\",\"visitTimeStart\":1555664196175,\"visitTimeEnd\":1618822596175}],\"flag\":0,\"time\":1555664196172}";
		channel.basicPublish("ex_fushi_account_d", "account_key", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}

}
