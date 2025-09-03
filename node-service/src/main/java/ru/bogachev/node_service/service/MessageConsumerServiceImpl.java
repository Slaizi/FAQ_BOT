package ru.bogachev.node_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bogachev.node_service.controller.MessageController;

@Service
@RequiredArgsConstructor
public class MessageConsumerServiceImpl implements MessageConsumerService {

    private final MessageController messageController;

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.queues.textMessageUpdate}")
    public void consumeTextMessageUpdate(Update update) {
        messageController.processTextMessage(update);
    }

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.queues.photoMessageUpdate}")
    public void consumePhotoMessageUpdate(Update update) {
        messageController.processPhotoMessage(update);
    }
}
