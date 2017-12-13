package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.fxmodels.AdminFxModel;

public class SupAResetPasswordSceneController {

    public SupAResetPasswordSceneController(AdminFxModel selectedAdminFxModel) {
        this.adminFxModel = selectedAdminFxModel;
    }

    private AdminFxModel adminFxModel;
    StringProperty newPassword = new SimpleStringProperty();

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
        adminNameLabel.setText(adminFxModel.getUserName());

        newPasswordTextField.textProperty().bindBidirectional(
                newPassword);

        confirmPasswordButton.setOnAction(eh -> {
            if (!(newPassword.get().equals(null) && newPassword.get().equals(""))) {
                adminFxModel.setPassword(newPassword.get());
                Admin admin = adminFxModel.getAdmin();
                Admin a = DaoFactory.INSTANCE.getAdminDao().save(admin);
                confirmPasswordButton.getScene().getWindow().hide();
            } else {
                //nejaky alert
            }
        });

    }
}
