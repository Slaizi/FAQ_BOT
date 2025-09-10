package ru.bogachev.node_service.service;

import ru.bogachev.node_service.data.dto.message.BaseMessageDto;

public interface MessageProducerService {

    void produce(BaseMessageDto message);

}
