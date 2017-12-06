package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.business.RegistrationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.AdminFxModel;
import sk.upjs.ics.bookwarehouse.storage.AdminDao;

public class SupANewAdminSceneController {

    private  AdminFxModel adminFxModel = new AdminFxModel();
    private  AdminDao adminDao = DaoFactory.INSTANCE.getAdminDao();
   

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
            Admin admin = adminFxModel.getAdmin();
            admin.setId();
            if (registrationIsOk(admin)) {
                admin = adminDao.save(admin);
                addAdminButton.getScene().getWindow().hide();
            }
        });
    }

    private boolean registrationIsOk(Admin a) {
        if (a.getEmail() == null || a.getEmail().equals("")) {
            return false;
        }
        if (a.getUserName() == null || a.getUserName().equals("")) {
            return false;
        }
        if (a.getPassword() == null || a.getPassword().equals("")) {
            return false;
        }
        if (!RegistrationManager.isNewUserName(a.getUserName())) {
            return false;
        }
        if (!RegistrationManager.isNewAdminEmail(a.getEmail())) {
            return false;
        }
        return true;
    }
}
