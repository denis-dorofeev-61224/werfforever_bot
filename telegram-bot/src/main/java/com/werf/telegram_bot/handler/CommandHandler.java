package com.werf.telegram_bot.handler;

import com.werf.telegram_bot.handler.commands.HelpCommand;
import com.werf.telegram_bot.handler.commands.ManagerCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.werf.telegram_bot.handler.commands.ZakazCommand;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommandHandler {
    private final Map<String, BotCommand> commands = new HashMap<>();

    public CommandHandler(HelpCommand helpCommand, ManagerCommand managerCommand,ZakazCommand zakazCommand) {
        commands.put("/help", helpCommand);
        commands.put("/manager", managerCommand);
        commands.put("/zakaz", zakazCommand);
        // Сюда позже можно добавить и другие команды
    }

    public void handle(Update update, TelegramBotHandler bot) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (text.startsWith("/")) {
                BotCommand command = commands.get(text);
                if (command != null) {
                    command.execute(update, bot);
                } else {
                    bot.sendMessage(chatId, "Неизвестная команда. Введите /help для списка команд.");
                }
            } else {
                // Пока просто эхо, позже заменим на парсинг напоминаний
                bot.sendMessage(chatId, "Ты написал: " + text);
            }
        }
    }
}