package ru.bogachev.dispatcher_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.bogachev.dispatcher_service.app.TelegramBot;
import ru.bogachev.dispatcher_service.service.DispatcherService;

@Configuration
@EnableConfigurationProperties(BotProperties.class)
@RequiredArgsConstructor
public class BotConfig {

    private final BotProperties botProperties;
    private final DispatcherService dispatcherService;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(
                botOptions(),
                botProperties.getName(),
                botProperties.getToken(),
                dispatcherService
        );
    }

    @Bean
    public DefaultBotOptions botOptions() {
        return new DefaultBotOptions();
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramBot());
        return telegramBotsApi;
    }

}
