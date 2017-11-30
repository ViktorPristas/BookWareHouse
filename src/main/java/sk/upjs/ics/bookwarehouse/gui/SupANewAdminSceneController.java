package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.upjs.ics.bookwarehouse.fxmodels.AdminFxModel;

public class SupANewAdminSceneController {

    AdminFxModel adminFxModel = new AdminFxModel();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button addAdminButton;

    @FXML
    void initialize() {
        userNameTextField.textProperty().bindBidirectional(
                adminFxModel.userNameProperty());

        emailTextField.textProperty().bindBidirectional(
                adminFxModel.emailProperty());

        passwordTextField.textProperty().bindBidirectional(
                adminFxModel.passwordProperty());

        addAdminButton.setOnAction(eh -> {
            addAdminButton.getScene().getWindow().hide();
        });
    }
}
