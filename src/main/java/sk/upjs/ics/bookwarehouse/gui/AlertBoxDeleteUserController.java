package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AlertBoxDeleteUserController {
    
    public AlertBoxDeleteUserController(String string){
        userLabel.setText(string);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private Label userLabel;

    @FXML
    void initialize() {
        deleteButton.setOnAction(eh -> {
            deleteButton.getScene().getWindow().hide();
        });

    }
}
