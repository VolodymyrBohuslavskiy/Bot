import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main extends TelegramLongPollingBot {
    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Main());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private void sendFoto(Message message, File cat) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setPhoto(cat);

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMess(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);                           //Розмітка
        sendMessage.setChatId(message.getChatId().toString());      //Вибір чату
//        sendMessage.setReplyToMessageId(message.getMessageId());    //ІД повідомлення на яке ми відповідаємо, виділення повідомлення
        sendMessage.setText(text);

        try {
            execute(sendMessage);//execute(sendMessage) замість sendMessage(sendMessage)
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private String aguagu() {
        char[] chars = {'a', 'g', 'b', 'h', 'u', 'd', 'w', '-'};
        char[] word = new char[new Random().nextInt(100) + 21];

        for (int i = 0; i < word.length; i++) {
            word[i] = chars[new Random().nextInt(chars.length - 1)];
        }
        String addw = new String(word);
        return "A" + addw+" !!!";
    }

    private File randomCat() {
        File catDir = new File("src/main/resources/topcats");
        File[] catsArr = catDir.listFiles();
        List<File> catlist = Arrays.asList(catsArr);
        return catlist.get(new Random().nextInt(catlist.size()));
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.hasText()) {
            sendFoto(message, randomCat());
            sendMess(message, aguagu());
        }
    }

    public String getBotUsername() {
        return "Crazy cat lady";
    }

    public String getBotToken() {
        return "788394404:AAFq3Et_I7TXz9Nx6m10YGTw8gXTcWA2M_w";
        // IF 409 Eror, you need new Token !
    }
}
