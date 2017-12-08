package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;
import sk.upjs.ics.bookwarehouse.storage.BookLendingDao;

public class ConfirmLendingSceneController {

    private BookFxModel bookFxModel;
    private IntegerProperty number = new SimpleIntegerProperty(0);
    private BookLendingDao bookLendingDao = DaoFactory.INSTANCE.getBookLendingDao();
    private StringProperty commentProperty = new SimpleStringProperty();

    public ConfirmLendingSceneController(BookFxModel bookFxModel) {
        this.bookFxModel = bookFxModel;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;
    //???
    @FXML
    private TextField commentTextField;

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
    private Label numberInStockTextField;

    @FXML
    void initialize() {
        authorLabel.setText(bookFxModel.getAuthor());

        titleLabel.setText(bookFxModel.getTitle());

        classLabel.setText(bookFxModel.getSchoolClass());

        numberInStockTextField.setText("" + bookFxModel.getNumberInStock());

        numberOfElementsTextField.textProperty().bindBidirectional(
                number, new NumberStringConverter());

        commentTextField.textProperty().bindBidirectional(
                commentProperty);

        confirmButton.setOnAction(eh -> {
            Book book = bookFxModel.getBook();
            System.out.println(book.getId());
            if (number.get() > 0 && number.get() <= book.getNumberInStock()) {
                Teacher teacher = DaoFactory.INSTANCE.getTeacherDao().findById(UserIdentificationManager.getId());
                BookLending bookLending = new BookLending(book, teacher, number.get(), commentProperty.get());
                bookLendingDao.save(bookLending);

                //editing the number of books in stock
                book.setNumberInStock(book.getNumberInStock() - number.get());
                book.setNumberOfUsed(book.getNumberOfUsed() + number.get());
                DaoFactory.INSTANCE.getBookDao().save(book);
                confirmButton.getScene().getWindow().hide();
            } else {
                AlertBoxWrongNumberOfBooksController controller = new AlertBoxWrongNumberOfBooksController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("AlertBoxWrongNumberOfBooks.fxml"));
                    loader.setController(controller);

                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);

                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("BookWareHouse");
                    stage.show();

                    // toto sa vykona az po zatvoreni okna
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        });
    }
}
