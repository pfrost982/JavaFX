package visual_lesson3;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField textField;

    @FXML
    private Label label;

    @FXML
    void buttonPressed() {
        String input = textField.getText();
        if (!input.isBlank()) {
            try {
                int number = Integer.parseInt(input);
                label.setText(String.valueOf(number));
            } catch (NumberFormatException e) {
                //label.setText("Ошибка! Введите число");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Введите число");
                alert.show();
            }
        }
    }

}
