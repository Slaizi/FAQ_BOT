package ru.bogachev.dispatcher_service.handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bogachev.dispatcher_service.service.MessageProducerService;

@Service
public class TextMessageHandler implements UpdateHandler {

    private final String textMessageQueue;
    private final MessageProducerService messageProducerService;

    public TextMessageHandler(
            @Value("${spring.rabbitmq.queues.textMessageUpdate}")
            String textMessageQueue,
            MessageProducerService messageProducerService) {
        this.textMessageQueue = textMessageQueue;
        this.messageProducerService = messageProducerService;
    }

    @Override
    public boolean canHandler(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    @Override
    public void handle(Update update) {
        messageProducerService.produce(textMessageQueue, update);
    }
}
