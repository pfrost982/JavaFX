package visual_lesson3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField textField;

    @FXML
    private Label label;

    @FXML
    void buttonPressed() throws IOException {
        //inputInt();
        Parent test = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("Test Second Window");
        newWindow.setScene(new Scene(test));
        newWindow.show();
    }

    void inputInt() {
        String input = textField.getText();
        textField.clear();
        if (!input.isBlank()) {
            try {
                int number = Integer.parseInt(input);
                label.setText(String.valueOf(number));
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введено не число");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Поле ввода пустое");
            alert.show();
        }
    }

    @FXML
    void secondButtonPressed() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Нехер жать эту кнопку");
        alert.show();
    }

}
