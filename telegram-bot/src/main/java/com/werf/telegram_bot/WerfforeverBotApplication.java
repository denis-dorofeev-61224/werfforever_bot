package com.werf.telegram_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WerfforeverBotApplication {
	public static void main(String[] args) {
		System.out.println("🚀 WerfForever Bot запускается...");
		SpringApplication.run(WerfforeverBotApplication.class, args);
	}
}