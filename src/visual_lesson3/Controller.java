package visual_lesson3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class Controller {
    final private int MAXTRYNUM = 7;
    private int tryNum;
    private int rand;
    private boolean firstTimeFlag;

    //------------------------------------------------------------ main_choice

    @FXML
    void guessNumber() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("guessNumber.fxml"));
        Parent guessNumber_fxml = loader.load();
        Stage guessNumberWindow = new Stage();
        guessNumberWindow.initModality(APPLICATION_MODAL);
        guessNumberWindow.setScene(new Scene(guessNumber_fxml));
        Controller controller = loader.getController();
        guessNumberWindow.setTitle("Угадай число");
        controller.firstTimeFlag = true;
        controller.tryNum = 1;
        controller.setGuessNumberLabel("Попытка: 1/" + MAXTRYNUM);
        guessNumberWindow.show();
    }

    @FXML
    void guessWord() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("guessWord.fxml"));
        Parent guessNumber_fxml = loader.load();
        Stage guessNumberWindow = new Stage();
        guessNumberWindow.initModality(APPLICATION_MODAL);
        guessNumberWindow.setScene(new Scene(guessNumber_fxml));
        Controller controller = loader.getController();
        guessNumberWindow.setTitle("Угадай слово");
        controller.firstTimeFlag = true;
        controller.tryNum = 1;
        controller.setGuessWordLabel2Label("Попытка: 1/" + MAXTRYNUM);
        guessNumberWindow.show();

    }

    //----------------------------------------------------------- guessNumber

    @FXML
    private Label guessNumberLabel1;

    @FXML
    private Label guessNumberLabel2;

    @FXML
    private TextField guessNumberTextField;

    @FXML
    private Button guessNumberButton;

    void setGuessNumberLabel(String s) {
        guessNumberLabel2.setText(s);
    }

    @FXML
    void guessNumberButton() {
        if (firstTimeFlag) {
            firstTimeFlag = false;
            rand = (int) (Math.random() * 100 + 1);
        }
        int answ = getInt();
        if (answ > 0) {
            tryNum++;
            if (tryNum > MAXTRYNUM) {
                guessNumberLabel1.setText("К сожалению у вас закончились попытки...");
                guessNumberLabel2.setText("Было загадано число:");
                guessNumberButton.setVisible(false);
                guessNumberTextField.setText(" " + rand + " ");
                guessNumberTextField.setEditable(false);
            } else if (answ > rand) {
                guessNumberLabel1.setText("Ваше число больше загаданного " + rand);//для отладки
                guessNumberLabel2.setText("Попытка: " + tryNum + "/" + MAXTRYNUM);
            } else if (answ < rand) {
                guessNumberLabel1.setText("Ваше число меньше загаданного " + rand);//для отладки
                guessNumberLabel2.setText("Попытка: " + tryNum + "/" + MAXTRYNUM);
            } else if (answ == rand) {
                guessNumberLabel1.setText("Поздравляем вы угадали!!!");
                guessNumberButton.setVisible(false);
                guessNumberTextField.setText(" " + rand + " ");
                guessNumberTextField.setEditable(false);
            }
        }


    }

    int getInt() {
        String input = guessNumberTextField.getText();
        guessNumberTextField.clear();
        if (!input.isBlank()) {
            try {
                int number = Integer.parseInt(input);
                return number;
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введено не число");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Поле ввода пустое");
            alert.show();
        }
        return -1;
    }

    //----------------------------------------------------------- guessWord

    private String[] words = {"apple", "orange", "lemon", "banana",
            "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon",
            "leak", "kiwi", "mango", "mushroom", "nut",
            "olive", "pea", "peanut", "pear", "pepper",
            "pineapple", "pumpkin", "potato"};

    private String answ;

    @FXML
    private Button guessWordButton;

    @FXML
    private TextField guessWordTextField;

    @FXML
    private Label guessWordLabel2;

    @FXML
    private Label guessWordLabel1;

    void setGuessWordLabel2Label(String s) {
        guessWordLabel2.setText(s);
    }

    @FXML
    void guessWordButton() {
        if (firstTimeFlag) {
            firstTimeFlag = false;
            rand = (int) (Math.random() * words.length);
        }
        String word = words[rand];
        String answ = getWord();
        if (answ != "") {
            guessWordLabel1.setText(fillMake(word, answ) + " Отладка: " + word); //для отладки
            tryNum++;
            if (tryNum > MAXTRYNUM) {
                guessWordLabel1.setText("К сожалению у вас закончились попытки...");
                guessWordLabel2.setText("Было загадано слово:");
                guessWordButton.setVisible(false);
                guessWordTextField.setText(" " + word + " ");
                guessWordTextField.setEditable(false);
            } else if (answ == word) {
                guessWordLabel1.setText("Поздравляем вы угадали!!!");
                guessWordButton.setVisible(false);
                guessWordTextField.setText(" " + word + " ");
                guessWordTextField.setEditable(false);
            } else guessWordLabel2.setText("Попытка: " + tryNum + "/" + MAXTRYNUM);
        }

    }

    String getWord() {
        String input = guessWordTextField.getText();
        guessWordTextField.clear();
        if (!input.isBlank()) {
            return input;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Поле ввода пустое");
            alert.show();
            return "";
        }
    }

    String fillMake(String word, String answ) {
        StringBuilder fill = new StringBuilder(15);
        int wordLength;
        int i = 0;
        if (word.length() > answ.length()) wordLength = answ.length();
        else wordLength = word.length();

        for (; i < wordLength; i++) {
            if (word.charAt(i) == answ.charAt(i)) fill.append(word.charAt(i));
            else fill.append("#");
        }
        for (; i < 15; i++) fill.append("#");

        return fill.toString();
    }
}
