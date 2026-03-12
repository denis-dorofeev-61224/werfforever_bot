package  com.werf.telegram_bot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
// мы создали класс который по сути есть паспорт бота с именем и идентефикатором(токен)
@Data
@Configuration
@ConfigurationProperties(prefix = "telegram.bot")
public class BotConfig {
    private String username;
    private String token;
}