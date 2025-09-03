package ru.bogachev.dispatcher_service.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {

    boolean canHandler(Update update);

    void handle(Update update);

}
