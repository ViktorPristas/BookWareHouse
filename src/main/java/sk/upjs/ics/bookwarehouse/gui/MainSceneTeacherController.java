package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainSceneTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private MenuItem exitItem;

    @FXML
    private MenuItem openDBItem;

    @FXML
    private MenuItem showMyBooksItem;

    @FXML
    private MenuItem lendBookItem;

    @FXML
    private MenuItem returnBookItem;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private MenuItem accountSettingsItem;

    @FXML
    private MenuItem logOutItem;

    @FXML
    void initialize() {
       openDBItem.setOnAction(eh -> {
            MainSceneTeacherWithDBController controller = new MainSceneTeacherWithDBController();
            try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("MainSceneTeacherWithDB.fxml"));
            loader.setController(controller);
            
            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);
            
            
            Stage stage = (Stage) pane.getScene().getWindow();
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
