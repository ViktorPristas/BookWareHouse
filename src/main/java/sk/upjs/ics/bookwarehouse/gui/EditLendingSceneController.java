package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditLendingSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirmRequestButton;

    @FXML
    private Label authorLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label schoolClassLabel;

    @FXML
    private Label teacherLabel;

    @FXML
    private TextField numberOfBooksLabel;

    @FXML
    private Button deleteRequestButton;

    @FXML
    void initialize() {
        confirmRequestButton.setOnAction(eh -> {
            confirmRequestButton.getScene().getWindow().hide();
        });

        deleteRequestButton.setOnAction(eh -> {
            deleteRequestButton.getScene().getWindow().hide();
        });
    }
}
