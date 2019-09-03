package com.example.demo;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer {

	private final static String QUEUE_NAME = "q_fushi_account";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("ruxin.gtdreamlife.com");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("Greentown123");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		boolean autoAck = false;
		channel.basicConsume(QUEUE_NAME, autoAck, "ex_fushi_account_d", new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String routingKey = envelope.getRoutingKey();
				String contentType = properties.getContentType();
				long deliveryTag = envelope.getDeliveryTag();
				System.out.println(new String(body));
				
				// (process the message components here ...)
				channel.basicAck(deliveryTag, false);
			}
		});
	}

}
