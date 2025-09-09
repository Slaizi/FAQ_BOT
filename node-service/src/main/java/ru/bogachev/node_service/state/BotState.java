package ru.bogachev.node_service.state;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.bogachev.node_service.command.BotCommandRegister;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BotState {

    FIRST_MONITOR(BotCommandRegister.FirstMonitorCommands.values());

    private final BotCommandRegister.MessageAction[] array;

}
