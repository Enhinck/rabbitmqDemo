package com.example.demo.rabbitmq.consumer.broadcast;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author hueb
 * Queue 随机名称  接收来自  Exchange  popup_message_exchange的消息
 */
@Service
@Slf4j
@RabbitListener(bindings = @QueueBinding(value =
@Queue(value = "", durable = "true", autoDelete = "true", exclusive = "true"),
exchange = @Exchange(value = "popup_message_exchange", type = ExchangeTypes.FANOUT, durable = "true")))
public class RabbitMQConsumer1 {

	@RabbitHandler
	public void process(String string) {
		log.info("RabbitMQConsumer1 Receive:{}", string);
	}

}
