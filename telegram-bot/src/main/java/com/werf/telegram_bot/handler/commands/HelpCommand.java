package com.werf.telegram_bot.handler.commands;

import com.werf.telegram_bot.handler.BotCommand;
import com.werf.telegram_bot.handler.TelegramBotHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelpCommand implements BotCommand {
    @Override
    public void execute(Update update, TelegramBotHandler bot) {
        long chatId = update.getMessage().getChatId();
        String helpText = "Я бот компании Верфь. Вот что я умею:\n" +
                "/start - приветствие\n" +
                "/help - эта справка\n" +
                "/manager - связаться с менеджером\n" +
                "/zakaz - как заказать с доставкой в Балашиху\n" +
                "А ещё ты можешь отправить мне напоминание в формате: 12.04.2025 15:00 Твой текст, и я напомню тебе об этом в нужное время.";
        bot.sendMessage(chatId, helpText);
    }
}