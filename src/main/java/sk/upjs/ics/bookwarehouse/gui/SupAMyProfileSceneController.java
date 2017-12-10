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
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.SuperAdmin;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.SuperAdminFxModel;
import sk.upjs.ics.bookwarehouse.storage.SuperAdminDao;

public class SupAMyProfileSceneController {

    private SuperAdminFxModel superAdminFxModel = new SuperAdminFxModel();
    private SuperAdminDao superAdminDao = DaoFactory.INSTANCE.getSuperAdminDao();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button saveChangesButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField repeatPasswordTextField;

    @FXML
    void initialize() {
        superAdminFxModel.loadActualSuperAdminToModel(UserIdentificationManager.getId());
        
        saveChangesButton.setOnAction(eh -> {
            SuperAdmin superAdmin = superAdminFxModel.getSuperAdmin();
            if (modificationIsOk(superAdmin)) {
                saveChangesButton.getScene().getWindow().hide();
                superAdminDao.save(superAdmin);
            } else {
                // mozno nejaky alert
            }
            
        });

        userNameTextField.textProperty().bindBidirectional(superAdminFxModel.userNameProperty());

        passwordTextField.textProperty().bindBidirectional(superAdminFxModel.passwordProperty());

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
    
    private boolean modificationIsOk(SuperAdmin superAdmin) {
        if (superAdmin.getUserName() == null || superAdmin.getUserName().equals("")) {
            return false;
        }
        List<SuperAdmin> list = superAdminDao.getAll();
        for (SuperAdmin a : list) {
            if ((!a.getId().equals(superAdmin.getId()) && (a.getUserName().equals(superAdmin.getUserName())))) {
                return false;
            }
        }
        return true;
    }
}
