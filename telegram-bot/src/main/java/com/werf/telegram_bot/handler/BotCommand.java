package com.werf.telegram_bot.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommand {
    void execute(Update update, TelegramBotHandler bot);
}