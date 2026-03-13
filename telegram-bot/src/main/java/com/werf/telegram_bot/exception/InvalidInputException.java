package com.werf.telegram_bot.exception;
// создаем это класс для задела при развитии проетка.Пока неверный вввод обрабатывает
//CommandHandler

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}