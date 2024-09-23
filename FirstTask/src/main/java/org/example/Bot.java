package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    public void send(Long chatID, String text){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatID));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public String response(String message){
        if(message.equals("/start")){
            return "Привет! Я Текстовый бот. Напиши /help, чтобы узнать больше";
        }
        else if(message.equals("/help")){
            return "Я Текстовый бот, у меня есть несколько функций:\n 1) Я могу отправлять Вам, что Вы ввели в чат \n 2) Если напишите /help, то я Вам расскажу о себе";

        }
        else{
            return "Вы ввели: " + message;
        }
    }
    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        var text = message.getText();
        var userID = message.getFrom().getId();
        send(userID, response(text));
    }

    @Override
    public String getBotUsername() {
        return "Test_1311Bot";
    }
    @Override
    public String getBotToken() {

        return "7827445352:AAGIA0i7SNSWgJeK-fmvU--rzfPRWoq1HXo";
    }
}
