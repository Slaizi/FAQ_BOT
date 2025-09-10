package ru.bogachev.node_service.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.bogachev.node_service.data.dto.message.BaseMessageDto;
import ru.bogachev.node_service.service.MessageProducerService;

@Service
public class MessageProducerServiceImpl implements MessageProducerService {

    private final String answerMessageQueue;
    private final RabbitTemplate rabbitTemplate;

    public MessageProducerServiceImpl(
            @Value("${spring.rabbitmq.queues.answerMessage}")
            String answerMessageQueue,
            RabbitTemplate rabbitTemplate) {
        this.answerMessageQueue = answerMessageQueue;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void produce(BaseMessageDto message) {
        rabbitTemplate.convertAndSend(answerMessageQueue, message);
    }
}
