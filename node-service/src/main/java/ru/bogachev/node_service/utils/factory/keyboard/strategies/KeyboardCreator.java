package ru.bogachev.node_service.utils.factory.keyboard.strategies;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ru.bogachev.node_service.command.BotCommandRegister;
import ru.bogachev.node_service.utils.factory.keyboard.KeyboardType;

public interface KeyboardCreator {

    KeyboardType getType();

    ReplyKeyboard create(BotCommandRegister.BotCmdActions[] commands);

}
