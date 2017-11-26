package sk.upjs.ics.bookwarehouse.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookWareHouseApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        LogInSceneController controller = new LogInSceneController();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("LogInScene.fxml"));
        loader.setController(controller);

        Parent parentPane = loader.load();
        Scene scene = new Scene(parentPane);

        stage.setScene(scene);
        stage.setTitle("BookWareHouse");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
