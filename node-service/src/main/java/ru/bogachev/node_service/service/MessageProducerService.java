package ru.bogachev.node_service.service;

import ru.bogachev.node_service.data.dto.BaseMessageDto;

public interface MessageProducerService {

    void produce(BaseMessageDto message);

}
