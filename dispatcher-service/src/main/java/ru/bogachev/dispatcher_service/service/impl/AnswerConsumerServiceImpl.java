package ru.bogachev.dispatcher_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.bogachev.dispatcher_service.data.dto.BaseMessageDto;
import ru.bogachev.dispatcher_service.processor.SenderProcessor;
import ru.bogachev.dispatcher_service.service.AnswerConsumerService;

@Service
@RequiredArgsConstructor
public class AnswerConsumerServiceImpl implements AnswerConsumerService {

    private final SenderProcessor senderProcessor;

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.queues.answerMessage}")
    public void consume(BaseMessageDto message) {
        senderProcessor.sendProcess(message);
    }
}
