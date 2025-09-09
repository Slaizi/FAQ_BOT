package ru.bogachev.node_service.data.dto;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class SendMessageDto extends BaseMessageDto {

    public SendMessageDto(Long chatId, String output,
                          ReplyKeyboard keyboard, String type) {
        super(chatId, output, keyboard, type);
    }
}
