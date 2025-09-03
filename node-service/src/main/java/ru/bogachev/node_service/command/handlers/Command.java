package ru.bogachev.node_service.command.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    void handleUpdate(Update update);

}
