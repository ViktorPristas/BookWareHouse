package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;

public class ConfirmLendingSceneController {
    private BookFxModel bookFxModel;
    
    public ConfirmLendingSceneController(BookFxModel bookFxModel) {
        this.bookFxModel = bookFxModel;
        bookFxModel.setNumberInStockBefore(bookFxModel.getNumberInStock());
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField numberOfElementsTextField;

    @FXML
    private Button confirmButton;

    @FXML
    private Label authorLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label classLabel;

    @FXML
    void initialize() {
        confirmButton.setOnAction(eh -> {
            confirmButton.getScene().getWindow().hide();
        });
    }
}
