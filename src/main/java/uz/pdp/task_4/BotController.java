package uz.pdp.task_4;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static uz.pdp.task_4.BotService.getOrCreatUser;

public class BotController {
    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        BotService.telegramBot.setUpdatesListener(updates ->{
            for (Update update : updates) {
                executorService.execute(()->{
                    handleUpdate(update);
                });
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
    });
    }

    private void handleUpdate(Update update) {
        Message message = update.message();
        if (message != null && message.chat() != null) {
            Long chatId = update.message().chat().id();
            TgUser tgUser = getOrCreatUser(chatId);
            String firstname = message.from().firstName();
            String lastname = message.from().lastName();

            if (message.text() != null) {
                BotService.acceptUserAndSendWelcome(tgUser, chatId, firstname, lastname);
            } else if (message.contact() != null) {
                Contact contact = message.contact();
                if (tgUser.getTgState().equals(TgState.SENDING_CONTACT)) {
                    BotService.acceptWelcomeAndSendPhone(tgUser, chatId, contact);
                }else if (tgUser.getTgState().equals(TgState.CHOOSING_GENDER)) {
                    BotService.acceptShowAndSendMarry(tgUser);
                }
            }
        }else if (update.callbackQuery() != null) {
            CallbackQuery callbackQuery = update.callbackQuery();
            String data = update.callbackQuery().data();
            Long id = callbackQuery.from().id();
            TgUser tgUser = getOrCreatUser(id);

            if (tgUser.getTgState().equals(TgState.SENDING_GENDER)){
            BotService.acceptGenderAndShow(tgUser, data);
            }
            else if (tgUser.getTgState().equals(TgState.CHOOSENG_MARRY)) {

                BotService.acceptMarryAndChoosing(tgUser, data);
            }

        }

    }
}
