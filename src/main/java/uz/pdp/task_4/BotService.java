package uz.pdp.task_4;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import uz.pdp.task_4.bot.DB;
import uz.pdp.task_4.bot.PhoneNumber;

public class BotService {
    public static TelegramBot telegramBot = new TelegramBot("7449264666:AAF8u0tmTIVTKcQDET8G-9joMuoI2G6AIac");

    public static TgUser getOrCreatUser(Long id) {
        for (TgUser tgUser : DB.TG_USERS) {
            if (tgUser.getId().equals(id)){
                return tgUser;
            }
        }
        TgUser tgUser = new TgUser();
        tgUser.setId(id);
        DB.TG_USERS.add(tgUser);
        return tgUser;
    }

    public static void acceptUserAndSendWelcome(TgUser tgUser, Long catId, String firstname, String lastname) {
        SendMessage sendMessage = new SendMessage(tgUser.getId(),
                """
                        Assalomu aleykum hurmatli %s %s
                        Botimizga xush kelibsiz
                        """.formatted(firstname, lastname));

        telegramBot.execute(sendMessage);
        SendMessage sendMessage1 = new SendMessage(tgUser.getId(),
                """
                        Iltimos kontakingizni yuboring.
                        """);
        KeyboardButton keyboardButton = new KeyboardButton("Sharing contact");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboardButton);
        keyboardButton.requestContact(true);
        replyKeyboardMarkup.resizeKeyboard(true);
        sendMessage1.replyMarkup(replyKeyboardMarkup);
        telegramBot.execute(sendMessage1);
        tgUser.setFirstname(firstname);
        tgUser.setLastname(lastname);
        tgUser.setTgState(TgState.SENDING_CONTACT);
    }

    public static void acceptWelcomeAndSendPhone(TgUser tgUser, Long chatId, Contact contact) {
        String phone = contact.phoneNumber();
        PhoneNumber.fix(phone);
        tgUser.setPhone(phone);
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        SendMessage sendMessage1 = new SendMessage(tgUser.getId(), "SHARING CONTACT REMOVED");
        sendMessage1.replyMarkup(replyKeyboardRemove);
        telegramBot.execute(sendMessage1);
        SendMessage sendMessage = new SendMessage(tgUser.getId(),
                """
                        Jinsingizni tanlang.
                        """);
//        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardMarkup inlineKeyboardMarkup  = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(
                new InlineKeyboardButton("MALE").callbackData("male"),
                new InlineKeyboardButton("FEMALE").callbackData("female")
        );
        sendMessage.replyMarkup(inlineKeyboardMarkup);
        SendResponse response = telegramBot.execute(sendMessage);
        Integer msgId = response.message().messageId();
        tgUser.setMessageId(msgId);
        tgUser.setTgState(TgState.SENDING_GENDER);
    }

    public static void acceptGenderAndShow(TgUser tgUser, String data) {
        String gender = "";
        if (data.startsWith("male")){
            gender = "male";
            tgUser.setTgGender("Male");
        } else if (data.startsWith("female")) {
            gender = "female";
            tgUser.setTgGender("Female");
        }

        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup(tgUser.getId(), tgUser.getMessageId());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        editMessageReplyMarkup.replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(editMessageReplyMarkup);
        SendMessage sendMessage = new SendMessage(tgUser.getId(),
                """
                        Are you married?
                        """);
        InlineKeyboardMarkup inlineKeyboardMarkup1 = new InlineKeyboardMarkup();
        inlineKeyboardMarkup1.addRow(
                new InlineKeyboardButton("Married").callbackData("Married"),
                new InlineKeyboardButton(" Unmarried").callbackData("NotMarried")
        );
        sendMessage.replyMarkup(inlineKeyboardMarkup1);
        SendResponse response = telegramBot.execute(sendMessage);
        Integer messageId = response.message().messageId();
        tgUser.setMessageId(messageId);
        tgUser.setTgState(TgState.CHOOSENG_MARRY);

    }

    public static void acceptShowAndSendMarry(TgUser tgUser) {

    }

    public static void acceptMarryAndChoosing(TgUser tgUser, String data) {
        String marry = "";
        if (data.startsWith("Married")){
            marry = "Married";
            tgUser.setMarried("Married");
        } else if (data.startsWith("NotMarried")) {
            marry = "Don't married." ;
            tgUser.setMarried("Unmarried");
        }

        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup(tgUser.getId(), tgUser.getMessageId());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        editMessageReplyMarkup.replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(editMessageReplyMarkup);
        SendMessage sendMessage = new SendMessage(tgUser.getId(),
                """
                Fullname: %s %s
                Phone:  %s
                Gender: %s
                Marry: %S
              
                """.formatted(tgUser.getFirstname(), tgUser.getLastname(), tgUser.getPhone(),tgUser.getTgGender(), tgUser.getMarried() ));
        telegramBot.execute(sendMessage);

        tgUser.setTgState(TgState.CHOOSING_GENDER);


    }
}
