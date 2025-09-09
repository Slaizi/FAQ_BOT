package ru.bogachev.dispatcher_service.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bogachev.dispatcher_service.processor.strategies.MessageSender;
import ru.bogachev.dispatcher_service.data.dto.BaseMessageDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SenderProcessor {

    private final List<MessageSender> senders;

    public void sendProcess(BaseMessageDto message) {
        senders.stream()
                .filter(f -> f.canSend(message))
                .findFirst()
                .ifPresent(p -> p.send(message));
    }

}
