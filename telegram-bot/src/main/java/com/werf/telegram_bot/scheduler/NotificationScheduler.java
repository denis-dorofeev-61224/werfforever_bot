package com.werf.telegram_bot.scheduler;

import com.werf.telegram_bot.handler.TelegramBotHandler;
import com.werf.telegram_bot.model.NotificationTask;
import com.werf.telegram_bot.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationScheduler {
    private final NotificationService notificationService;
    private final TelegramBotHandler botHandler;

    @Scheduled(cron = "0 * * * * *") // Каждую минуту в 0 секунд
    public void sendNotifications() {
        LocalDateTime now = LocalDateTime.now();
        List<NotificationTask> tasks = notificationService.getTasksForCurrentMinute(now);
        for (NotificationTask task : tasks) {
            // Коронная фраза про менеджера + заказ
            String messageToSend = "🔔 Напоминание: " + task.getMessage() +
                    "\n\nЕсли нужна помощь с заказом или доставкой в Балашиху — напиши менеджеру: /manager";
            botHandler.sendMessage(task.getChatId(), messageToSend);
            notificationService.deleteTask(task.getId());
        }
        if (!tasks.isEmpty()) {
            log.info("Отправлено {} уведомлений", tasks.size());
        }
    }
}