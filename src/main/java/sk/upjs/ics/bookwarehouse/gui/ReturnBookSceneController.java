package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReturnBookSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label authorLabel;

    @FXML
    private Label titelLabel;

    @FXML
    private Label schoolClassLabel;

    @FXML
    private Label teacherLabel;

    @FXML
    private TextField numberOfReturnedBooksTextField;

    @FXML
    private Button confirmButton;

    @FXML
    void initialize() {
        confirmButton.setOnAction(eh -> {
            confirmButton.getScene().getWindow().hide();
        });
    }
}
