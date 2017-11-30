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
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.ManagerFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.business.PasswordManager;
import sk.upjs.ics.bookwarehouse.business.RegistrationManager;
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
    private TextField surnameTextField;

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

        surnameTextField.textProperty().bindBidirectional(
                teacherFxModel.surnameProperty());

        emailTextField.textProperty().bindBidirectional(
                teacherFxModel.emailProperty());

        emailTextField.textProperty().bindBidirectional(
                teacherFxModel.emailProperty());

        passwordTextField.textProperty().bindBidirectional(
                teacherFxModel.passwordProperty());
        
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
                    stage.setScene(scene);
                    stage.setTitle("BookWareHouse");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                    signUpButton.getScene().getWindow().hide();
                } else {
                    //nejaky allert 
                }
                // toto sa vykona az po zatvoreni okna
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
        if (!RegistrationManager.isNewUserName(t.getEmail())) {
            return false;
        }
        return true;
    }
}
