package com.werf.telegram_bot.handler;

import com.werf.telegram_bot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBotHandler extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final CommandHandler commandHandler;  // Добавили поле для обработчика команд

    // Внедряем через конструктор и BotConfig, и CommandHandler
    public TelegramBotHandler(BotConfig botConfig, CommandHandler commandHandler) {
        this.botConfig = botConfig;
        this.commandHandler = commandHandler;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Проверяем, есть ли сообщение и текст
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Отладка: видим, что пришло
            System.out.println("📩 Получено обновление: " + messageText);

            // Обработка команды /start (оставляем здесь твой крутой текст)
            if ("/start".equals(messageText)) {
                sendMessage(chatId, "Привет! Я бот компании Верфь 🏅\n\n" +
                        "Если ты живешь в Балашихе — ценишь время и не хочешь мотаться в Москву за подарками себе и близким, ТО ✨\n\n" +
                        "Я ПОМОГУ ТЕБЕ:\n" +
                        "— узнать о наших изделиях из кожи (переходи на сайт: https://werfstore.ru)\n" +
                        "— связаться с менеджером (/manager)\n" +
                        "— создать напоминание о важном деле (просто отправь дату и текст, например: 12.04.2025 15:00 Позвонить клиенту)\n" +
                        "— получить ответы на частые вопросы (/help)");
            } else {
                // Все остальные сообщения (включая /help, /manager и прочее) передаём в CommandHandler
                commandHandler.handle(update, this);
            }
        }
    }

    // Метод сделал public, чтобы CommandHandler мог отправлять сообщения
    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
            log.info("Сообщение отправлено в чат {}: {}", chatId, text);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения: {}", e.getMessage());
        }
    }
}