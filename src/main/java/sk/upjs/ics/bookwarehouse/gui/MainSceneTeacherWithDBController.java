package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainSceneTeacherWithDBController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        assert exitItem != null : "fx:id=\"exitItem\" was not injected: check your FXML file 'MainSceneTeacherWithDB.fxml'.";
        assert openDBItem != null : "fx:id=\"openDBItem\" was not injected: check your FXML file 'MainSceneTeacherWithDB.fxml'.";
        assert showMyBooksItem != null : "fx:id=\"showMyBooksItem\" was not injected: check your FXML file 'MainSceneTeacherWithDB.fxml'.";
        assert lendBookItem != null : "fx:id=\"lendBookItem\" was not injected: check your FXML file 'MainSceneTeacherWithDB.fxml'.";
        assert returnBookItem != null : "fx:id=\"returnBookItem\" was not injected: check your FXML file 'MainSceneTeacherWithDB.fxml'.";
        assert aboutItem != null : "fx:id=\"aboutItem\" was not injected: check your FXML file 'MainSceneTeacherWithDB.fxml'.";
        assert accountSettingsItem != null : "fx:id=\"accountSettingsItem\" was not injected: check your FXML file 'MainSceneTeacherWithDB.fxml'.";
        assert logOutItem != null : "fx:id=\"logOutItem\" was not injected: check your FXML file 'MainSceneTeacherWithDB.fxml'.";

    }
}
