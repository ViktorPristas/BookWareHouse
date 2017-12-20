package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;
import sk.upjs.ics.bookwarehouse.storage.BookDao;

public class AdminNewBookSceneController {

    private BookFxModel bookFxModel = new BookFxModel();
    private BookDao bookDao = DaoFactory.INSTANCE.getBookDao();

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

        schoolClassComboBox.getItems().addAll(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "I. G", "II. G", "III. G", "IV. G");

        numberInStockTextField.textProperty().bindBidirectional(
                bookFxModel.numberInStockProperty(), new NumberStringConverter());

        authorTextField.textProperty().bindBidirectional(
                bookFxModel.AuthorProperty());

        titleTextField.textProperty().bindBidirectional(
                bookFxModel.TitleProperty());

        yearofPublicationTextField.textProperty().bindBidirectional(
                bookFxModel.yearOfPublicationProperty(), new NumberStringConverter());

//        schoolClassComboBox.textProperty().bindBidirectional(
//                bookFxModel.SchoolClassProperty());
        schoolClassComboBox.valueProperty().bindBidirectional(
                bookFxModel.SchoolClassProperty());

        saveButton.setOnAction(eh -> {
            Book b = bookFxModel.getBook();
            b.setNumberOfUsed(0);
            b.setUsed(false);
            b.setId();
            if (registrationIsOk(b)) {
                bookDao.save(b);
                saveButton.getScene().getWindow().hide();
            } else {
                //nejaky alert
            }
        });
    }

    private boolean registrationIsOk(Book book) {
        if (book.getAuthor() == null || book.getAuthor().equals("")) {
            return false;
        }
        if (book.getTitle() == null || book.getTitle().equals("")) {
            return false;
        }
        if (book.getYearOfPublication() == 0) {
            return false;
        }
        if (book.getSchoolClass() == null || book.getSchoolClass().equals("")) {
            return false;
        }
        if (numberInStockTextField == null || numberInStockTextField.equals("")) {
            return false;
        }

        return true;
    }
}
