package ru.bogachev.node_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bogachev.node_service.command.BotCommandRegister;
import ru.bogachev.node_service.command.handlers.Command;
import ru.bogachev.node_service.service.StateCachingService;
import ru.bogachev.node_service.state.BotState;

import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MessageController {

    private final StateCachingService stateCachingService;
    private final ApplicationContext applicationContext;

    public void processTextMessage(Update update) {
        Long userId = update.getMessage().getChatId();
        String msg = update.getMessage().getText();
        Deque<BotState> states = stateCachingService.get(userId);

        Arrays.stream(Objects.requireNonNull(states.peek()).getCommands())
                .filter(f -> f.getValue().equals(msg))
                .findFirst()
                .ifPresent(command -> {
                    Command handle = getCommandHandler(command);
                    handle.handleUpdate(update, states);
                    stateCachingService.save(userId, states);
                });
    }

    private Command getCommandHandler(BotCommandRegister.BotCmdActions action) {
        try {
            return action.getCommandHandler(applicationContext);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить бин для команды " + action.getValue(), e);
        }

    }



    public void processPhotoMessage(Update update) {

    }

}

