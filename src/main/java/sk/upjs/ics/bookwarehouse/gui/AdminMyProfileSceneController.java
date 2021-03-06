package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.ManagerFactory;
import sk.upjs.ics.bookwarehouse.business.PasswordManager;
import sk.upjs.ics.bookwarehouse.business.DefaultRegistrationManager;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.AdminFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.TeacherFxModel;
import sk.upjs.ics.bookwarehouse.storage.AdminDao;
import sk.upjs.ics.bookwarehouse.storage.TeacherDao;

public class AdminMyProfileSceneController {

    private AdminFxModel adminFxModel = new AdminFxModel();
    private AdminDao adminDao = DaoFactory.INSTANCE.getAdminDao();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button saveChangesButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField repeatPasswordTextField;

    @FXML
    void initialize() {
        adminFxModel.loadActualAdminToModel(UserIdentificationManager.getId());

        saveChangesButton.setOnAction(eh -> {
            Admin admin = adminFxModel.getAdmin();
            if (modificationIsOk(admin)) {
                saveChangesButton.getScene().getWindow().hide();
                adminDao.save(admin);
            } else {
                //nejaky alert
            }
        });

        userNameTextField.textProperty().bindBidirectional(
                adminFxModel.userNameProperty());

        emailTextField.textProperty().bindBidirectional(
                adminFxModel.emailProperty());

        passwordTextField.textProperty().bindBidirectional(
                adminFxModel.passwordProperty());

        passwordTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (passwordTextField.getText().equals(repeatPasswordTextField.getText())) {
                    passwordTextField.setStyle("-fx-background-color: white;");
                    repeatPasswordTextField.setStyle("-fx-background-color: white;");
                    saveChangesButton.setDisable(false);
                } else {
                    passwordTextField.setStyle("-fx-background-color: red;");
                    repeatPasswordTextField.setStyle("-fx-background-color: red;");
                    saveChangesButton.setDisable(true);

                }
            }
        });

        repeatPasswordTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (passwordTextField.getText().equals(repeatPasswordTextField.getText())) {
                    passwordTextField.setStyle("-fx-background-color: white;");
                    repeatPasswordTextField.setStyle("-fx-background-color: white;");
                    saveChangesButton.setDisable(false);
                } else {
                    passwordTextField.setStyle("-fx-background-color: red;");
                    repeatPasswordTextField.setStyle("-fx-background-color: red;");
                    saveChangesButton.setDisable(true);
                }
            }
        });

    }

    private boolean modificationIsOk(Admin admin) {
        if (admin.getEmail() == null || admin.getEmail().equals("")) {
            return false;
        }
        if (admin.getUserName() == null || admin.getUserName().equals("")) {
            return false;
        }
        List<Admin> list = adminDao.getAll();
        for (Admin a : list) {
            if ((!a.getId().equals(admin.getId()) && (a.getEmail().equals(admin.getEmail())))) {
                return false;
            }
        }
        return true;
    }
}
