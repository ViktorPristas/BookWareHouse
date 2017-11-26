package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainSceneAdminRequestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button returnBookButton;

    @FXML
    private Button editLendingButton;

    @FXML
    void initialize() {
        backButton.setOnAction(eh -> {
            MainSceneAdminController controller = new MainSceneAdminController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("MainSceneAdmin.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        editLendingButton.setOnAction(eh -> {
            EditLendingSceneController controller = new EditLendingSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("EditLendingScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        returnBookButton.setOnAction(eh -> {
            ReturnBookSceneController controller = new ReturnBookSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ReturnBookScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("BookWareHouse");
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
    }
}
