package ru.bogachev.node_service.command.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bogachev.node_service.state.BotState;

import java.util.Deque;

public abstract class Command {

    public abstract void handleUpdate(Update update, BotState state);

    /**
     * По умолчанию ничего не делает.
     * Команды, которые меняют состояние, могут переопределить.
     */
    public void handleStatePutNextState(Deque<BotState> states) {
        // default: do nothing
    }

    public void handleUpdate(Update update, Deque<BotState> states) {
        handleUpdate(update, states.peek());
        handleStatePutNextState(states);
    }

}
