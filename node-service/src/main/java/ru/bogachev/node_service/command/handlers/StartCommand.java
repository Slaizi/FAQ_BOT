package ru.bogachev.node_service.command.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bogachev.node_service.data.dto.SendMessageDto;
import ru.bogachev.node_service.service.MessageProducerService;
import ru.bogachev.node_service.state.BotState;
import ru.bogachev.node_service.utils.factory.keyboard.KeyboardFactory;

import static ru.bogachev.node_service.data.dto.MessageType.SEND_MESSAGE;
import static ru.bogachev.node_service.utils.factory.keyboard.KeyboardType.REPLY_KEYBOARD_DEF;

@Component
@RequiredArgsConstructor
public class StartCommand extends Command {

    private final KeyboardFactory keyboardFactory;
    private final MessageProducerService messageProducerService;

    @Override
    public void handleUpdate(Update update, BotState state) {
        messageProducerService.produce(createSendMessageDto(update, state));
    }

    private SendMessageDto createSendMessageDto(Update update, BotState state) {
        return new SendMessageDto(
                update.getMessage().getChatId(),
                "\uD83D\uDC4B Привет %s!\nНапиши свой вопрос здесь — и я постараюсь помочь ✨".formatted(update.getMessage().getChat().getFirstName()),
                keyboardFactory.createKeyboard(REPLY_KEYBOARD_DEF, state.getCommands()),
                SEND_MESSAGE.getValue()
        );
    }
}
