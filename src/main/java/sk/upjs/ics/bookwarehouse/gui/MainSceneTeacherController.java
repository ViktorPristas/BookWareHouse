package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button openDBButton;

    @FXML
    private Button myBooksButton;

    @FXML
    private Button myProfileButton;

    @FXML
    void initialize() {
       openDBButton.setOnAction(eh -> {
            MainSceneTeacherDBController controller = new MainSceneTeacherDBController();
            try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("MainSceneTeacherDB.fxml"));
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
       
       myBooksButton.setOnAction(eh -> {
            TeacherMyBooksSceneController controller = new TeacherMyBooksSceneController();
            try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("TeacherMyBooksScene.fxml"));
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



    