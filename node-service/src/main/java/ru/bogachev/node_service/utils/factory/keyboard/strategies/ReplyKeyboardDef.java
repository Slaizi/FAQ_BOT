package ru.bogachev.node_service.utils.factory.keyboard.strategies;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.bogachev.node_service.command.BotCommandRegister;
import ru.bogachev.node_service.utils.factory.keyboard.KeyboardType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ReplyKeyboardDef implements KeyboardCreator {

    public KeyboardType getType() {
        return KeyboardType.REPLY_KEYBOARD_DEF;
    }

    @Override
    public ReplyKeyboard create(BotCommandRegister.BotCmdActions[] commands) {
        ReplyKeyboardMarkup markup = createReplyKeyboardWithSettings();
        List<KeyboardRow> keyboard = createFormattedKeyboardDef(commands);
        markup.setKeyboard(keyboard);
        return markup;
    }

    private List<KeyboardRow> createFormattedKeyboardDef(BotCommandRegister.BotCmdActions[] commands) {
        List<KeyboardRow> keyboard = new ArrayList<>();
        int totalCommand = commands.length;
        int half = totalCommand / 2;
        boolean isOdd = totalCommand % 2 != 0;

        KeyboardRow firstRow = buildKeyboardRow(commands, 0,
                (half + (isOdd ? 1 : 0))
        );
        keyboard.add(firstRow);

        KeyboardRow secondRow = buildKeyboardRow(commands,
                (half + (isOdd ? 1 : 0)), totalCommand);
        keyboard.add(secondRow);

        return keyboard;
    }

    private KeyboardRow buildKeyboardRow(BotCommandRegister.BotCmdActions[] commands, int start, int end) {
        return IntStream.range(start, end)
                .mapToObj(i -> commands[i].getValue())
                .filter(v -> !v.startsWith("/"))
                .map(KeyboardButton::new)
                .collect(Collectors.toCollection(KeyboardRow::new));
    }

    private ReplyKeyboardMarkup createReplyKeyboardWithSettings() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }

}
