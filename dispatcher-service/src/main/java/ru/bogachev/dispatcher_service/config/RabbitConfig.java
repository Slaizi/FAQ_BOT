package ru.bogachev.dispatcher_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private final String textMessageUpdate;
    private final String photoMessageUpdate;
    private final String answerMessage;

    public RabbitConfig(
            @Value("${spring.rabbitmq.queues.textMessageUpdate}")
            String textMessageUpdate,
            @Value("${spring.rabbitmq.queues.photoMessageUpdate}")
            String photoMessageUpdate,
            @Value("${spring.rabbitmq.queues.answerMessage}")
            String answerMessage) {
        this.textMessageUpdate = textMessageUpdate;
        this.photoMessageUpdate = photoMessageUpdate;
        this.answerMessage = answerMessage;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue textMessageQueue() {
        return new Queue(textMessageUpdate, true);
    }

    @Bean
    public Queue photoMessageQueue() {
        return new Queue(photoMessageUpdate, true);
    }

    @Bean
    public Queue answerMessageQueue() {
        return new Queue(answerMessage, true);
    }
}
