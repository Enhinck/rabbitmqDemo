package com.example.demo.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${greentown.rabbitmq.queue}")
    String queueName;

    @Value("${greentown.rabbitmq.exchange}")
    String exchange;

    @Value("${greentown.rabbitmq.routingkey}")
    private String routingkey;

    /*
     * @Autowired
     *
     * @Lazy(true) private RabbitTemplate rabbitTemplate;
     */
    /*
     * @Bean Queue queue() { RabbitAdmin admin = new
     * RabbitAdmin(rabbitTemplate.getConnectionFactory());
     *
     * String queueName = UUID.randomUUID().toString(); Queue queue = new
     * Queue(queueName, true, true, true); admin.declareQueue(queue); return queue;
     * }
     */

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(exchange);
    }

    @Bean(name = "popupMessageExchange")
    FanoutExchange popupMessageExchange() {
        return new FanoutExchange("popup_message_exchange");
    }

    /*
     * @Bean Binding binding(Queue queue, FanoutExchange exchange) { return
     * BindingBuilder.bind(queue).to(exchange); }
     */

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
