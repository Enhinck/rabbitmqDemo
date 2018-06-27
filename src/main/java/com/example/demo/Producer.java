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
		factory.setHost("192.168.9.51");
		factory.setUsername("guest");
		factory.setPassword("guest");
		Connection connection = factory.newConnection();
		return connection;
	}

	public static void exchange() throws Exception {
		Connection connection = getConnection();
		Channel channel = connection.createChannel();
		// channel.exchangeDeclare(QUEUE_NAME, false, false, false, null)
		// channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!111";
		channel.basicPublish("callback_msg", "test", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}

}
