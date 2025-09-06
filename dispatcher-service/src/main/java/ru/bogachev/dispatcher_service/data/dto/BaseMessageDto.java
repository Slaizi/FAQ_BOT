package ru.bogachev.dispatcher_service.data.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(
        @JsonSubTypes.Type(value = SendMessageDto.class, name = "sendMessage")
)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseMessageDto implements Serializable {

    private Long chatId;
    private String output;
    private ReplyKeyboard keyboard;

}
