package sk.upjs.ics.bookwarehouse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    //BE AWARE THIS IS A TEXTFIELD :)
    @FXML
    private TextField numberOfBooksLabel;

    @FXML
    private Button deleteRequestButton;

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
            int number = numberOfBooksActually.get();
            System.out.println(number);
            if (number <= (book.getNumberInStock() + bookLendingFxModel.getLended())) {
                BookLending bookLending = DaoFactory.INSTANCE.getBookLendingDao().findById(bookLendingFxModel.getId());
                bookLending.setLended(number);
                bookLending.setApproved(true);
                bookLendingDao.save(bookLending);

                //editing the number of books in stock
                book.setNumberInStock(book.getNumberInStock() - numberOfBooksActually.get());
                book.setNumberOfUsed(book.getNumberOfUsed() + numberOfBooksActually.get());
                DaoFactory.INSTANCE.getBookDao().save(book);

                confirmRequestButton.getScene().getWindow().hide();
            } else {
                //nejaky alert, ze chce privela knih
            }
        });

        deleteRequestButton.setOnAction(eh -> {
            deleteRequestButton.getScene().getWindow().hide();
        });
    }
}
