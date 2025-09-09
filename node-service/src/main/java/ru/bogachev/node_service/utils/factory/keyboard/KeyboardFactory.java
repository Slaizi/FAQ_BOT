package ru.bogachev.node_service.utils.factory.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ru.bogachev.node_service.command.BotCommandRegister;
import ru.bogachev.node_service.utils.factory.keyboard.strategies.KeyboardCreator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class KeyboardFactory {

    private final Map<KeyboardType, KeyboardCreator> map;

    public KeyboardFactory(List<KeyboardCreator> keyboards) {
        this.map = keyboards.stream()
                .collect(Collectors.toMap(
                        KeyboardCreator::getType,
                        Function.identity()
                ));
    }

    public ReplyKeyboard createKeyboard(KeyboardType type,
                                        BotCommandRegister.BotCmdActions[] commands) {
        return Optional.ofNullable(map.get(type))
                .map(func -> func.create(commands))
                .orElseThrow(() -> new IllegalArgumentException("Keyboard type not supported: %s".formatted(type)));
    }
}
