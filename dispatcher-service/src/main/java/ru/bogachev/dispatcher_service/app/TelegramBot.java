package ru.bogachev.dispatcher_service.app;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bogachev.dispatcher_service.service.DispatcherService;

public class TelegramBot extends TelegramLongPollingBot {

    private final String botName;
    private final DispatcherService dispatcherService;

    public TelegramBot(DefaultBotOptions options, String botName, String botToken,
                       DispatcherService dispatcherService) {
        super(options, botToken);
        this.botName = botName;
        this.dispatcherService = dispatcherService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        dispatcherService.dispatch(update);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    public void sendAnswerMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException ignored) {
            }
        }
    }


}
