package com.werf.telegram_bot.handler.commands;

import com.werf.telegram_bot.handler.BotCommand;
import com.werf.telegram_bot.handler.TelegramBotHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ZakazCommand implements BotCommand {
    @Override
    public void execute(Update update, TelegramBotHandler bot) {
        long chatId = update.getMessage().getChatId();
        String instruction = "Если ты живёшь в Балашихе и не хочешь ехать в Москву, терять время и деньги на доставку, мы предлагаем удобную схему:\n\n" +
                "1️⃣ Изучи наши изделия на сайте: https://werfstore.ru\n" +
                "2️⃣ Напиши менеджеру (/manager) — он проконсультирует по товарам, срокам изготовления и доставки в Москву.\n" +
                "3️⃣ Менеджер создаст заказ и вышлет ссылку на оплату.Ссылка действует сутки\n" +
                "4️⃣ После оплаты этот же менеджер привезёт товар на площадь Славы (Балашиха).\n" +
                "5️⃣ Чек придёт на электронную почту.\n" +
                "6️⃣ Если потребуется возврат или ремонт — ты всегда можешь самостоятельно обратиться в любой магазин сети.\n\n" +
                "Экономь время и деньги с нами! Не переплачивай жадным транспортникам и курьерам!)) 👜";
        bot.sendMessage(chatId, instruction);
    }
}