package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminEditBookSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField yearofPublicationTextField;

    @FXML
    private TextField schoolClassTextField;

    @FXML
    private TextField numberInStockTextField;

    @FXML
    private Button saveButton;

    @FXML
    void initialize() {
        
        saveButton.setOnAction(eh -> {
            saveButton.getScene().getWindow().hide();
        });

    }
}
