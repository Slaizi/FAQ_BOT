package ru.bogachev.dispatcher_service.processor.strategies;

import ru.bogachev.dispatcher_service.data.dto.BaseMessageDto;

public interface MessageSender {

    boolean canSend(BaseMessageDto message);

    void send(BaseMessageDto message);

}
