package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlertBoxDeleteRequestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    void initialize() {
        deleteButton.setOnAction(eh -> {
            deleteButton.getScene().getWindow().hide();
        });
    }
}
