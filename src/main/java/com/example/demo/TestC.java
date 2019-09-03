package com.example.demo;

import com.example.demo.service.RabbitMQSender;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 事件接收处理唯一入口（一条消息只有一个服务端处理）（Queue event_process） 所有事件发送入口callback_exchange
 *
 * @author hueb
 */
@Slf4j
@Service
@RabbitListener(bindings = @QueueBinding(value =
@Queue(value = "q_app_message_sys", durable = "true", autoDelete = "false", exclusive = "false"),
        exchange = @Exchange(value = "ex_app_message_d", type = ExchangeTypes.DIRECT, durable = "true"), key = "sys"))
@Data
public class TestC {

    @Resource
    private RabbitMQSender rabbitMQSender;
    public static void main(String[] args) {

        TestC testC = null;

        System.out.println(Optional.ofNullable(null).isPresent());
       // System.out.println(Optional.ofNullable(testC).ifPresent(testC1 -> .););
    }
    @RabbitHandler
    public void process(byte[] string) {
        log.info("q_app_message_sys Receive:{}", new String(string));
    }

}
