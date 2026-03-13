package com.werf.telegram_bot.repository;
import com.werf.telegram_bot.model.NotificationTask;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
    List<NotificationTask> findByNotificationTimeBetween(LocalDateTime start, LocalDateTime end);
}
