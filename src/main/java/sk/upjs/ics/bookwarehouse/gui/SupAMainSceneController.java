package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SupAMainSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminsButton;

    @FXML
    private Button logsButton;

    @FXML
    private Button logOutButton;

    @FXML
    void initialize() {
        adminsButton.setOnAction(eh -> {
            SupAAdminsSceneController controller = new SupAAdminsSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("SupAAdminsScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = (Stage) adminsButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        logsButton.setOnAction(eh -> {
            SupALogsSceneController controller = new SupALogsSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("SupALogsScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = (Stage) logsButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        logOutButton.setOnAction(eh -> {
            LogInSceneController controller = new LogInSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("LogInScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                logOutButton.getScene().getWindow().hide();
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
    }
}
