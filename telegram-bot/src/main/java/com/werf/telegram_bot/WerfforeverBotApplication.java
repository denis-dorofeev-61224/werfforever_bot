package com.werf.telegram_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // <-- Внедрение шедулера
public class WerfforeverBotApplication {
	public static void main(String[] args) {
		System.out.println("🚀 WerfForever Bot запускается...");
		SpringApplication.run(WerfforeverBotApplication.class, args);
	}
}