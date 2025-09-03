package ru.bogachev.node_service.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageConsumerService {

    void consumeTextMessageUpdate(Update update);

    void consumePhotoMessageUpdate(Update update);

}
