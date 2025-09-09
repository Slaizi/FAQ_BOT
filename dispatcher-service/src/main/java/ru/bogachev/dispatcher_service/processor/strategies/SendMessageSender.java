package ru.bogachev.dispatcher_service.processor.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.bogachev.dispatcher_service.app.TelegramBot;
import ru.bogachev.dispatcher_service.data.dto.BaseMessageDto;
import ru.bogachev.dispatcher_service.data.dto.SendMessageDto;

@Service
@RequiredArgsConstructor
public class SendMessageSender implements MessageSender {

    private final TelegramBot telegramBot;

    @Override
    public boolean canSend(BaseMessageDto message) {
        return message instanceof SendMessageDto;
    }

    @Override
    public void send(BaseMessageDto message) {
        telegramBot.sendAnswerMessage(mapToSendMessage(message));
    }

    private SendMessage mapToSendMessage(BaseMessageDto dtoMessage) {
        return SendMessage.builder()
                .chatId(dtoMessage.getChatId())
                .text(dtoMessage.getOutput())
                .replyMarkup(dtoMessage.getKeyboard())
                .build();
    }
}
