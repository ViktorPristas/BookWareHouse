package sk.upjs.ics.bookwarehouse.fxmodels;

import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.business.BookLendingManager;
import sk.upjs.ics.bookwarehouse.storage.BookLendingDao;

public class BookLendingFxModel {

    private ObservableList<BookLending> lendings = new SimpleListProperty<>();
    private ObservableList<BookLendingFxModel> bookLendingsModel = FXCollections.observableArrayList();
    private LongProperty id = new SimpleLongProperty();
    private LongProperty teacherId = new SimpleLongProperty();
    private LongProperty book = new SimpleLongProperty();
    private IntegerProperty yearOfReturn = new SimpleIntegerProperty();
    private IntegerProperty lended = new SimpleIntegerProperty();
    private IntegerProperty returned = new SimpleIntegerProperty();
    private IntegerProperty lost = new SimpleIntegerProperty();
    private StringProperty comment = new SimpleStringProperty();
    private BooleanProperty approved = new SimpleBooleanProperty();

    public BookLendingFxModel() {
        BookLendingDao bookLendingDao = DaoFactory.INSTANCE.getBookLendingDao();
        List<BookLending> lendings = bookLendingDao.getAll(); // povodne
        this.lendings = FXCollections.observableArrayList(lendings);
    }

    public ObservableList<BookLendingFxModel> getBookLendingsModel() {
        return bookLendingsModel;
    }

    public ObservableList<BookLending> getLendings() {
        return lendings;
    }

    public void setLendings(ObservableList<BookLending> lendings) {
        this.lendings = lendings;
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public LongProperty IdProperty() {
        return id;
    }

    public Long getTeacher() {
        return teacherId.get();
    }

    public void setTeacher(Long teacher) {
        this.teacherId.set(teacher);
    }

    public LongProperty teacherProperty() {
        return teacherId;
    }

    public Long getBook() {
        return book.get();
    }

    public void setBook(Long book) {
        this.book.set(book);
    }

    public LongProperty bookProperty() {
        return book;
    }

    public Integer getYearOfReturn() {
        return yearOfReturn.get();
    }

    public void setYearOfReturn(Integer yearOfReturn) {
        this.yearOfReturn.set(yearOfReturn);
    }

    public IntegerProperty yearOfReturnProperty() {
        return yearOfReturn;
    }

    public Integer getLended() {
        return lended.get();
    }

    public void setLended(Integer lended) {
        this.lended.set(lended);
    }

    public IntegerProperty lendedProperty() {
        return lended;
    }

    public Integer getReturned() {
        return returned.get();
    }

    public void setReturned(Integer returned) {
        this.returned.set(returned);
    }

    public IntegerProperty returnedProperty() {
        return lended;
    }

    public Integer getLost() {
        return lost.get();
    }

    public void setLost(Integer lost) {
        this.lost.set(lost);
    }

    public IntegerProperty lostProperty() {
        return lost;
    }

    public StringProperty getCommentProperty() {
        return comment;
    }

    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public Boolean getApproved() {
        return approved.get();
    }

    public void setApproved(Boolean approved) {
        this.approved.set(approved);
    }

    public BooleanProperty approvedProperty() {
        return approved;
    }

    public void loadBookLendingToModel(Long id) {
        bookLendingsModel.clear();
        for (BookLending bookLending : lendings) {
            if (bookLending.getTeacher().getId() == id) {

                BookLendingFxModel bookLendingFxModel = new BookLendingFxModel();

                // Long teacherId = bookLending.getTeacher().getId();
                //String;
                int yearOfReturn = bookLending.getYearOfReturn();
                int lended = bookLending.getLended();
                int returned = bookLending.getReturned();
                //lost;
                String comment = bookLending.getComment();
                // TODO
                //Boolean approved = bookLending.getApproved();

                // bookLendingFxModel.setTeacher(teacherId);
                bookLendingFxModel.setBook(bookLending.getBook().getId());
                bookLendingFxModel.setTeacher(bookLending.getTeacher().getId());
                bookLendingFxModel.setYearOfReturn(yearOfReturn);
                bookLendingFxModel.setLended(lended);
                bookLendingFxModel.setReturned(returned);
                bookLendingFxModel.setComment(comment);

                bookLendingsModel.add(bookLendingFxModel);
            }

        }
    }

    public void loadLendingForAdminToModel() {
        bookLendingsModel.clear();
        for (BookLending bookLending : lendings) {

            BookLendingFxModel bookLendingFxModel = new BookLendingFxModel();

            Book b = bookLending.getBook();
            // Long teacherId = bookLending.getTeacher().getId();
            //String;
            int yearOfReturn = bookLending.getYearOfReturn();
            int lended = bookLending.getLended();
            int returned = bookLending.getReturned();
            //lost;
            String comment = bookLending.getComment();
            // TODO
            //Boolean approved = bookLending.getApproved();

            // bookLendingFxModel.setTeacher(teacherId);
            //bookLendingFxModel.setBook(bookLending.getBook().getId());
            bookLendingFxModel.setTeacher(bookLending.getTeacher().getId());
            bookLendingFxModel.setYearOfReturn(yearOfReturn);
            bookLendingFxModel.setLended(lended);
            bookLendingFxModel.setReturned(returned);
            bookLendingFxModel.setComment(comment);

            bookLendingsModel.add(bookLendingFxModel);
        }

    }

    public void loadFilteredTeachersToModel(String selectedTeacher) {
        bookLendingsModel.clear();
        for (BookLending bookLending : lendings) {
            String formatedNameOfTeacher = bookLending.getTeacher().getName() + " " + bookLending.getTeacher().getSurname();
            if (formatedNameOfTeacher.equals(selectedTeacher) || selectedTeacher.equals("<Všetko>")) {

                BookLendingFxModel bookLendingFxModel = new BookLendingFxModel();

                int yearOfReturn = bookLending.getYearOfReturn();
                int lended = bookLending.getLended();
                int returned = bookLending.getReturned();
                //lost;
                String comment = bookLending.getComment();
                // TODO
                //Boolean approved = bookLending.getApproved();

                //bookLendingFxModel.setTeacher(teacherId);
//                bookLendingFxModel.setBook(bookLending.getBook().getId());
                bookLendingFxModel.setTeacher(bookLending.getTeacher().getId());
                bookLendingFxModel.setYearOfReturn(yearOfReturn);
                bookLendingFxModel.setLended(lended);
                bookLendingFxModel.setReturned(returned);
                bookLendingFxModel.setComment(comment);

                bookLendingsModel.add(bookLendingFxModel);
            }
        }
    }

}
