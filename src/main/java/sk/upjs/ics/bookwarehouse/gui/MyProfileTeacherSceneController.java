package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.business.RegistrationManager;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.TeacherFxModel;
import sk.upjs.ics.bookwarehouse.storage.TeacherDao;

public class MyProfileTeacherSceneController {

    TeacherFxModel teacherFxModel = new TeacherFxModel();
    TeacherDao teacherDao = DaoFactory.INSTANCE.getTeacherDao();

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
        teacherFxModel.loadActualTeacherToModel(UserIdentificationManager.getId());

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

        saveChangesButton.setOnAction(eh -> {
            Teacher teacher = teacherFxModel.getTeacher();
            if (registrationIsOk(teacher)) {
                teacher = teacherDao.save(teacher);
                saveChangesButton.getScene().getWindow().hide();
            } else {
                //nejaky alert
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
        return true;
    }
}
