//класс отвечающий за КОНФИГУРАЦИЮ и РЕГИСТРАЦИЮ БИНОВ
package com.werf.telegram_bot.config;

import com.werf.telegram_bot.handler.TelegramBotHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotInitializer {

    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramBotHandler botHandler) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(botHandler);
        System.out.println("🚀 Бот успешно зарегистрирован!");
        return api;
    }
}