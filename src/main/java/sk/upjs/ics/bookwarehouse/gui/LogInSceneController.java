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

public class LogInSceneController {

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
    void initialize() {
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
        });
        
        
    }
}
