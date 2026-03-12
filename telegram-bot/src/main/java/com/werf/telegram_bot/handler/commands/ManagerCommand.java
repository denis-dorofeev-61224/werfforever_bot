package com.werf.telegram_bot.handler.commands;

import com.werf.telegram_bot.handler.BotCommand;
import com.werf.telegram_bot.handler.TelegramBotHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ManagerCommand implements BotCommand {
    @Override
    public void execute(Update update, TelegramBotHandler bot) {
        long chatId = update.getMessage().getChatId();
        // Замени на свой реальный юзернейм в Telegram
        String managerContact = "Связаться с менеджером можно по ссылке: https://t.me/suportMax10101";
        bot.sendMessage(chatId, managerContact);
    }
}