package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.ManagerFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.business.PasswordManager;
import sk.upjs.ics.bookwarehouse.business.DefaultRegistrationManager;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.TeacherFxModel;
import sk.upjs.ics.bookwarehouse.storage.TeacherDao;

public class RegistrationSceneController {

    private final TeacherFxModel teacherFxModel = new TeacherFxModel();
    private final TeacherDao teacherDao = DaoFactory.INSTANCE.getTeacherDao();
    private final PasswordManager passwordManager = ManagerFactory.INSTANCE.getPasswordManager();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surenameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField passwordConfTextField;

    @FXML
    private TextField numberOfStudentsTextField;

    @FXML
    void initialize() {

        nameTextField.textProperty().bindBidirectional(
                teacherFxModel.nameProperty());

        surenameTextField.textProperty().bindBidirectional(
                teacherFxModel.surnameProperty());

        emailTextField.textProperty().bindBidirectional(
                teacherFxModel.emailProperty());

        passwordTextField.textProperty().bindBidirectional(
                teacherFxModel.passwordProperty());

        numberOfStudentsTextField.textProperty().bindBidirectional(
                teacherFxModel.numberOfStudentsInClassProperty(), new NumberStringConverter());

        passwordTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (passwordTextField.getText().equals(passwordConfTextField.getText())) {
                    passwordTextField.setStyle("-fx-background-color: white;");
                    passwordConfTextField.setStyle("-fx-background-color: white;");
                    signUpButton.setDisable(false);
                } else {
                    passwordTextField.setStyle("-fx-background-color: red;");
                    passwordConfTextField.setStyle("-fx-background-color: red;");
                    signUpButton.setDisable(true);

                }
            }
        });

        passwordConfTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (passwordTextField.getText().equals(passwordConfTextField.getText())) {
                    passwordTextField.setStyle("-fx-background-color: white;");
                    passwordConfTextField.setStyle("-fx-background-color: white;");
                    signUpButton.setDisable(false);
                } else {
                    passwordTextField.setStyle("-fx-background-color: red;");
                    passwordConfTextField.setStyle("-fx-background-color: red;");
                    signUpButton.setDisable(true);
                }
            }
        });

        signUpButton.setOnAction(eh -> {
            ThankYouForRegistrationSceneController controller = new ThankYouForRegistrationSceneController();
            try {
                Teacher teacher = teacherFxModel.getTeacher();
                teacher.setId();

                if (registrationIsOk(teacher)) {

                    teacher = teacherDao.save(teacher);

                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("ThankYouForRegistrationScene.fxml"));
                    loader.setController(controller);

                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);

                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("BookWareHouse");
                    stage.setResizable(false);
                    Image logo = new Image(getClass().getResourceAsStream("LogoBWH.png"));
                    stage.getIcons().add(logo);
                    stage.show();
                    signUpButton.getScene().getWindow().hide();
                } else {
                    showBadRegistrationWindow();
                }

            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
    }

    public boolean registrationIsOk(Teacher t) {
        if (t.getName() == null || t.getName().equals("")) {
            return false;
        }
        if ((t.getEmail() == null) || t.getEmail().equals("")) {
            return false;
        }
        if ((t.getSurname() == null) || t.getSurname().equals("")) {
            return false;
        }
        if (!DefaultRegistrationManager.isNewUserName(t.getEmail())) {
            return false;
        }
        if (t.getPassword() == null || t.getPassword().equals("")) {
            return false;
        }
        return true;
    }

    public void showBadRegistrationWindow() {
        AlertBoxFailToSignUpController controller = new AlertBoxFailToSignUpController();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("AlertBoxFailToSignUp.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("BookWareHouse");
            Image logo = new Image(getClass().getResourceAsStream("LogoBWH.png"));
            stage.getIcons().add(logo);
            stage.show();

        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
