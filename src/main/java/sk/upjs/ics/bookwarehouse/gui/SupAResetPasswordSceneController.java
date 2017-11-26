package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SupAResetPasswordSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminNameLabel;

    @FXML
    private TextField newPasswordTextField;

    @FXML
    private Button confirmPasswordButton;

    @FXML
    void initialize() {
        confirmPasswordButton.setOnAction(eh -> {
            confirmPasswordButton.getScene().getWindow().hide();
        });
    }
}
