package com.werf.telegram_bot.service;

import com.werf.telegram_bot.model.NotificationTask;
import com.werf.telegram_bot.repository.NotificationTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationTaskRepository repository;

    // Сохранить новую задачу
    public void saveNotification(Long chatId, String message, LocalDateTime notificationTime) {
        NotificationTask task = new NotificationTask();
        task.setChatId(chatId);
        task.setMessage(message);
        task.setNotificationTime(notificationTime);
        repository.save(task);
    }

    // Получить все задачи на указанную минуту (с учётом секунд = 0)
    public List<NotificationTask> getTasksForCurrentMinute(LocalDateTime now) {
        LocalDateTime start = now.withSecond(0).withNano(0);
        LocalDateTime end = start.plusMinutes(1);
        return repository.findByNotificationTimeBetween(start, end);
    }

    // Удалить задачу по id
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    // Вспомогательный метод для парсинга даты из строки (можно вынести в утилиту)
    public static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}