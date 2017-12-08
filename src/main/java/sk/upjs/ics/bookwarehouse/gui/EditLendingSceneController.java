package sk.upjs.ics.bookwarehouse.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.fxmodels.BookFxModel;
import sk.upjs.ics.bookwarehouse.fxmodels.BookLendingFxModel;
import sk.upjs.ics.bookwarehouse.storage.BookLendingDao;

public class EditLendingSceneController {

    private BookLendingFxModel bookLendingFxModel;
    private IntegerProperty numberOfBooksActually = new SimpleIntegerProperty();
    private BookLendingDao bookLendingDao = DaoFactory.INSTANCE.getBookLendingDao();

    public EditLendingSceneController(BookLendingFxModel selectedBookLendingFxModel) {
        this.bookLendingFxModel = selectedBookLendingFxModel;
        numberOfBooksActually.set(bookLendingFxModel.getLended());
    }

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
    void initialize() {

        Book book = DaoFactory.INSTANCE.getBookDao().findById(bookLendingFxModel.getBook());
        authorLabel.setText(book.getAuthor());
        titleLabel.setText(book.getTitle());
        schoolClassLabel.setText(book.getSchoolClass());
        Teacher teacher = DaoFactory.INSTANCE.getTeacherDao().findById(bookLendingFxModel.getTeacher());
        teacherLabel.setText(teacher.getName() + " " + teacher.getSurname());

        numberOfBooksLabel.textProperty().bindBidirectional(
                numberOfBooksActually, new NumberStringConverter());

        confirmRequestButton.setOnAction(eh -> {
            if (numberOfBooksActually.get() <= (book.getNumberInStock() + bookLendingFxModel.getLended())) {
                BookLending bookLending = DaoFactory.INSTANCE.getBookLendingDao().findById(bookLendingFxModel.getId());
                bookLending.setLended(numberOfBooksActually.get());
                bookLendingDao.save(bookLending);

                //editing the number of books in stock
                book.setNumberInStock(book.getNumberInStock() - numberOfBooksActually.get());
                book.setNumberOfUsed(book.getNumberOfUsed() + numberOfBooksActually.get());
                DaoFactory.INSTANCE.getBookDao().save(book);

                confirmRequestButton.getScene().getWindow().hide();
            } else {
                AlertBoxNumberOfBooksInLendingController controller = new AlertBoxNumberOfBooksInLendingController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("AlertBoxNumberOfBooksInLending.fxml"));
                    loader.setController(controller);

                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("BookWareHouse");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();

                    // toto sa vykona az po zatvoreni okna
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        });

    }
}
