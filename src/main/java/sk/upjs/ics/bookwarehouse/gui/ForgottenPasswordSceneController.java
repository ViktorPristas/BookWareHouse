package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ForgottenPasswordSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button sendPasswordButton;

    @FXML
    void initialize() {
        sendPasswordButton.setOnAction(eh -> {
            sendPasswordButton.getScene().getWindow().hide();
        });
    }
}
