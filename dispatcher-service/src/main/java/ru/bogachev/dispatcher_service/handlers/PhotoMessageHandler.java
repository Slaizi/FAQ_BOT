package ru.bogachev.dispatcher_service.handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bogachev.dispatcher_service.service.MessageProducerService;

@Service
public class PhotoMessageHandler implements UpdateHandler {

    private final String photoMessageQueue;
    private final MessageProducerService messageProducerService;

    public PhotoMessageHandler(
            @Value("${spring.rabbitmq.queues.photoMessageUpdate}")
            String photoMessageQueue,
            MessageProducerService messageProducerService) {
        this.photoMessageQueue = photoMessageQueue;
        this.messageProducerService = messageProducerService;
    }

    @Override
    public boolean canHandler(Update update) {
        return update.hasMessage() && update.getMessage().hasPhoto();
    }

    @Override
    public void handle(Update update) {
        messageProducerService.produce(photoMessageQueue, update);
    }
}
