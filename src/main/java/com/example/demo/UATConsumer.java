package com.example.demo;

import com.rabbitmq.client.*;

import java.io.IOException;

public class UATConsumer {

	private final static String QUEUE_NAME = "home";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("smart-dev.gtdreamlife.com");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("Greentown123");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		boolean autoAck = false;
		channel.basicConsume(QUEUE_NAME, autoAck, "ex_app_message_d", new DefaultConsumer(channel) {
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
