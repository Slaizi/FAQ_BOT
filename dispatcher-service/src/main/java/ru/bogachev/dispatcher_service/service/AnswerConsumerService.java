package ru.bogachev.dispatcher_service.service;

import ru.bogachev.dispatcher_service.data.dto.BaseMessageDto;

public interface AnswerConsumerService {

    void consume(BaseMessageDto message);

}
