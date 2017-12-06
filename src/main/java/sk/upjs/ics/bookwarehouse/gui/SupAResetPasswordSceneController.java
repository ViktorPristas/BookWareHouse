package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
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
                adminFxModel.passwordProperty());

        confirmPasswordButton.setOnAction(eh -> {
            if (!(adminFxModel.getPassword().equals(null) && adminFxModel.getPassword().equals(""))) {
                Admin admin = adminFxModel.getAdmin();
                System.out.println(admin.getPassword());
                DaoFactory.INSTANCE.getAdminDao().save(admin);
                confirmPasswordButton.getScene().getWindow().hide();
            } else {
                //nejaky alert
            }
        });

    }
}
