package com.werf.telegram_bot.handler;

import com.werf.telegram_bot.handler.commands.HelpCommand;
import com.werf.telegram_bot.handler.commands.ManagerCommand;
import com.werf.telegram_bot.handler.commands.ZakazCommand;
import com.werf.telegram_bot.service.NotificationService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandHandler {
    private final Map<String, BotCommand> commands = new HashMap<>();
    private final NotificationService notificationService;

    public CommandHandler(HelpCommand helpCommand,
                          ManagerCommand managerCommand,
                          ZakazCommand zakazCommand,
                          NotificationService notificationService) {
        this.notificationService = notificationService;
        commands.put("/help", helpCommand);
        commands.put("/manager", managerCommand);
        commands.put("/zakaz", zakazCommand);
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
                // Пытаемся распарсить как напоминание
                Pattern pattern = Pattern.compile("^(\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}) (.*)$");
                Matcher matcher = pattern.matcher(text);

                if (matcher.find()) {
                    String dateTimeStr = matcher.group(1);
                    String messageText = matcher.group(2);

                    try {
                        // Парсим дату и сохраняем
                        LocalDateTime notificationTime = NotificationService.parseDateTime(dateTimeStr);
                        notificationService.saveNotification(chatId, messageText, notificationTime);
                        bot.sendMessage(chatId, "✅ Напоминание сохранено! Я напомню тебе " + dateTimeStr);
                    } catch (DateTimeParseException e) {
                        // Ошибка формата даты
                        bot.sendMessage(chatId, "❌ Неправильный формат даты. Используй: дд.мм.гггг чч:мм текст");
                    } catch (Exception e) {
                        // Другие ошибки (БД и т.п.)
                        bot.sendMessage(chatId, "❌ Ошибка при сохранении. Попробуй позже.");
                        // Логируем ошибку для разработчика
                        System.err.println("Ошибка сохранения напоминания: " + e.getMessage());
                    }
                } else {
                    bot.sendMessage(chatId, "❓ Не понял команду. Введи /help для списка команд.\n" +
                            "Или отправь напоминание в формате: дд.мм.гггг чч:мм текст");
                }
            }
        }
    }
}