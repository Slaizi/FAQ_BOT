package ru.bogachev.dispatcher_service.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface DispatcherService {

    void dispatch(Update update);

}
