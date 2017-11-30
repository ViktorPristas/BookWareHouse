package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MyProfileTeacherSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button saveChangesButton;
   

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField numberOfStudentsTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField repeatPasswordTextField;

    @FXML
    void initialize() {
        
        
        saveChangesButton.setOnAction(eh -> {
            saveChangesButton.getScene().getWindow().hide();
        });

    }
}
