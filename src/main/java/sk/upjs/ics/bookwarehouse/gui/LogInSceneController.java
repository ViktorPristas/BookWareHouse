package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.ManagerFactory;
import sk.upjs.ics.bookwarehouse.business.PasswordManager;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.LoginFxModel;

public class LogInSceneController {

    LoginFxModel loginFxModel = new LoginFxModel();
    PasswordManager passwordManager = ManagerFactory.INSTANCE.getPasswordManager();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Hyperlink forgotPasswordHyperlink;

    @FXML
    private Button registerButton;

    @FXML
    private Button adminloginButton;

    @FXML
    private Button supadminloginButton;

    @FXML
    void initialize() {

        emailTextField.textProperty().bindBidirectional(
                loginFxModel.emailProperty());

        passwordTextField.textProperty().bindBidirectional(
                loginFxModel.passwordProperty());

        registerButton.setOnAction(eh -> {
            RegistrationSceneController controller = new RegistrationSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("RegistrationScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        loginButton.setOnAction(eh -> {
            if (!UserIdentificationManager.setUser(loginFxModel.getEmail())) {
                //nejaky allert
            }
            int typeOfUser = UserIdentificationManager.getTypeOfUser();
            if (passwordManager.isCorrectPassword(loginFxModel.getPassword(), typeOfUser, UserIdentificationManager.getId())) {
                if (typeOfUser == 1) {
                    loginTeacher();
                }
                if (typeOfUser == 2) {
                    loginAdmin();
                }
                if (typeOfUser == 3) {
                    loginSuperAdmin();
                }
            } else {
                //ten isty allert ako o 3 riadky vyssie
            }

        });

        forgotPasswordHyperlink.setOnAction(eh -> {
            ForgottenPasswordSceneController controller = new ForgottenPasswordSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ForgottenPasswordScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        adminloginButton.setOnAction(eh -> {
            loginAdmin();
        });

        supadminloginButton.setOnAction(eh -> {
            loginSuperAdmin();
        });
    }

    private void loginTeacher() {
        MainSceneTeacherController controller = new MainSceneTeacherController();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("MainSceneTeacher.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("BookWareHouse");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            loginButton.getScene().getWindow().hide();

            // toto sa vykona az po zatvoreni okna
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void loginAdmin() {
        MainSceneAdminController controller = new MainSceneAdminController();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("MainSceneAdmin.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("BookWareHouse");
            stage.show();
            adminloginButton.getScene().getWindow().hide();

            // toto sa vykona az po zatvoreni okna
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void loginSuperAdmin() {
        SupAMainSceneController controller = new SupAMainSceneController();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("SupAMainScene.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = (Stage) supadminloginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("BookWareHouse");
            stage.show();

            // toto sa vykona az po zatvoreni okna
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
