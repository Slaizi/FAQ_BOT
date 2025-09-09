package ru.bogachev.node_service.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class BaseMessageDto implements Serializable {

    private Long chatId;
    private String output;
    private ReplyKeyboard keyboard;
    private String type;

}
