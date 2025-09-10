package ru.bogachev.node_service.data.dto.message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MessageType {

    SEND_MESSAGE("sendMessage");

    private final String value;
}
