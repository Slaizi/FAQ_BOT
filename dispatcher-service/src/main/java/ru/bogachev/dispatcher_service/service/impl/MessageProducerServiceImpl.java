package ru.bogachev.dispatcher_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bogachev.dispatcher_service.service.MessageProducerService;

@Service
@RequiredArgsConstructor
public class MessageProducerServiceImpl implements MessageProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void produce(String rabbitQueue, Update update) {
        rabbitTemplate.convertAndSend(rabbitQueue, update);
    }
}
