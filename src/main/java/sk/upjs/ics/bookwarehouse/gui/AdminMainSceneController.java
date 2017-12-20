package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.BookLendingFxModel;

public class AdminMainSceneController {

    @FXML
    private AnchorPane pane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logOutButton;

    @FXML
    private Button openDBButton;

    @FXML
    private Button openRequestsButton;

    @FXML
    private Button editProfileButton;

    @FXML
    void initialize() {
        openDBButton.setOnAction(eh -> {
            AdminMainSceneDBController controller = new AdminMainSceneDBController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("AdminMainSceneDB.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        logOutButton.setOnAction(eh -> {
            // UserIdentificationManager.logOut();
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
                stage.show();
                logOutButton.getScene().getWindow().hide();

            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        editProfileButton.setOnAction(eh -> {
            AdminMyProfileSceneController controller = new AdminMyProfileSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("AdminMyProfileScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setTitle("BookWareHouse");
                stage.show();

            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        openRequestsButton.setOnAction(eh -> {
            AdminMainSceneRequestController controller = new AdminMainSceneRequestController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("AdminMainSceneRequest.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = (Stage) openRequestsButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

    }
}
