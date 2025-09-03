package ru.bogachev.dispatcher_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bogachev.dispatcher_service.handlers.UpdateHandler;
import ru.bogachev.dispatcher_service.service.DispatcherService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DispatcherServiceImpl implements DispatcherService {

    private final List<UpdateHandler> handlers;

    @Override
    public void dispatch(Update update) {
        for (UpdateHandler handler : handlers) {
            if (handler.canHandler(update)) {
                handler.handle(update);
                return;
            }
        }
    }
}
