package ru.bogachev.dispatcher_service.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageProducerService {

    void produce(String rabbitQueue, Update update);

}
