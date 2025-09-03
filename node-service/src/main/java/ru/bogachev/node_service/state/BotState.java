package ru.bogachev.node_service.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.bogachev.node_service.command.BotCommandRegister;

@AllArgsConstructor
@Getter
public enum BotState {

    FIRST_MONITOR(BotCommandRegister.FirstMonitorCommands.values());

    private final Object[] array;

}
