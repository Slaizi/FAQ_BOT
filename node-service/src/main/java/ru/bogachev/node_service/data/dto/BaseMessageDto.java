package ru.bogachev.node_service.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
public class BaseMessageDto implements Serializable {

    private Long chatId;
    private String output;
    private ReplyKeyboard keyboard;
    private String type;

}
