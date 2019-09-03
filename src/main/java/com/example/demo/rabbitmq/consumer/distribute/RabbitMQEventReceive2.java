package com.example.demo.rabbitmq.consumer.distribute;

import javax.annotation.Resource;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.service.RabbitMQSender;

import lombok.extern.slf4j.Slf4j;

/**
 * 事件接收处理唯一入口（一条消息只有一个服务端处理）（Queue event_process） 所有事件发送入口callback_exchange
 * 
 * @author hueb
 *
 */
@Slf4j
//@Service
@RabbitListener(bindings = @QueueBinding(value = 
@Queue(value = "event_process1", durable = "true", autoDelete = "false", exclusive = "false"), 
exchange = @Exchange(value = "callback_exchange", type = ExchangeTypes.FANOUT, durable = "true")))
public class RabbitMQEventReceive2 {

	@Resource
	RabbitMQSender rabbitMQSender;

	@RabbitHandler
	public void process(String string) {

		log.info("event_process2 Receive:{}", string);
		rabbitMQSender.send(string);
	}

}
