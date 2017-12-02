package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookEdit;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;
import sk.upjs.ics.bookwarehouse.storage.AdminDao;
import sk.upjs.ics.bookwarehouse.storage.BookDao;

public class AdminEditBookSceneController {

    private BookFxModel bookFxModel = new BookFxModel();
    private BookDao bookDao = DaoFactory.INSTANCE.getBookDao();

    public AdminEditBookSceneController(BookFxModel bookFxModel) {
        this.bookFxModel = bookFxModel;
        bookFxModel.setNumberInStockBefore(bookFxModel.getNumberInStock());
    }

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
        authorTextField.textProperty().bindBidirectional(
                bookFxModel.AuthorProperty());

        titleTextField.textProperty().bindBidirectional(
                bookFxModel.TitleProperty());

        yearofPublicationTextField.textProperty().bindBidirectional(
                bookFxModel.yearOfPublicationProperty(), new NumberStringConverter());

        numberInStockTextField.textProperty().bindBidirectional(
                bookFxModel.numberInStockProperty(), new NumberStringConverter());

        schoolClassComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "I. G", "II. G", "III. G", "IV. G");
        
        schoolClassComboBox.promptTextProperty().bindBidirectional(bookFxModel.SchoolClassProperty());

        saveButton.setOnAction(eh -> {
            Book book = bookFxModel.getBook();
            if (modificationIsOk(book)) {
                bookDao.save(book);
                if (bookFxModel.getNumberInStockBefore() != book.getNumberInStock()) {
                    System.out.println("pred " + bookFxModel.getNumberInStockBefore());
                    System.out.println("po " + book.getNumberInStock());
                    AdminDao adminDao = DaoFactory.INSTANCE.getAdminDao();
                    Admin admin = adminDao.findById(UserIdentificationManager.getId());
                    BookEdit bookEdit = new BookEdit(admin, book, bookFxModel.getNumberInStockBefore());
                    DaoFactory.INSTANCE.getBookEditDao().save(bookEdit);
                }
                saveButton.getScene().getWindow().hide();
            } else {
                //nejaky alert
            }
        });
    }

    private boolean modificationIsOk(Book book) {
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
