package ru.bogachev.node_service.command.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCommand implements Command {

    @Override
    public void handleUpdate(Update update) {

    }
}
