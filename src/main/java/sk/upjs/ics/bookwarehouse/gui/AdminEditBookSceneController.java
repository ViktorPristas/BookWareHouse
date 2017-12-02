package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> schoolClassComboBox;

    @FXML
    private TextField numberInStockTextField;

    @FXML
    private Button saveButton;

    @FXML
    void initialize() {
        
        saveButton.setOnAction(eh -> {
            saveButton.getScene().getWindow().hide();
        });
        
        schoolClassComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "I. G", "II. G", "III. G", "IV. G");


    }
}
