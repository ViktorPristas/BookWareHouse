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
    private LongProperty bookId = new SimpleLongProperty();
    private IntegerProperty yearOfReturn = new SimpleIntegerProperty();
    private IntegerProperty lended = new SimpleIntegerProperty();
    private IntegerProperty returned = new SimpleIntegerProperty();
    private IntegerProperty lost = new SimpleIntegerProperty();
    private StringProperty comment = new SimpleStringProperty();
    private BooleanProperty approved = new SimpleBooleanProperty();
    private StringProperty approvedString = new SimpleStringProperty();
    private StringProperty author = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty nameOfTeacher = new SimpleStringProperty();
    private StringProperty surnameOfTeacher = new SimpleStringProperty();

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
        return bookId.get();
    }

    public void setBook(Long book) {
        this.bookId.set(book);
    }

    public LongProperty bookProperty() {
        return bookId;
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
        return returned;
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

    public StringProperty getAuthorProperty() {
        return author;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public StringProperty nameOfTeacherProperty() {
        return nameOfTeacher;
    }

    public String getNameOfTeacher() {
        return this.nameOfTeacher.get();
    }

    public void setNameOfTeacher(String nameOfTeacher) {
        this.nameOfTeacher.set(nameOfTeacher);
    }

    public StringProperty surnameOfTeacherProperty() {
        return surnameOfTeacher;
    }

    public String getSurnameOfTeacher() {
        return this.surnameOfTeacher.get();
    }

    public void setSurnameOfTeacher(String surnameOfTeacher) {
        this.surnameOfTeacher.set(surnameOfTeacher);
    }

    public StringProperty getTitleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty ApprovedStringProperty() {
        return approvedString;
    }

    public String getApprovedString() {
        return approvedString.get();
    }

    public void setApprovedString(String approvedString) {
        this.approvedString.set(approvedString);
    }

    public void loadBookLendingToModel(Long teacherId) {
        bookLendingsModel.clear();
        for (BookLending bookLending : lendings) {
            if (bookLending.getTeacher().getId() == teacherId) {

                BookLendingFxModel bookLendingFxModel = new BookLendingFxModel();

                int yearOfReturn = bookLending.getYearOfReturn();
                int lended = bookLending.getLended();
                int returned = bookLending.getReturned();
                Book book = bookLending.getBook();
                String author = bookLending.getBook().getAuthor();
                String title = bookLending.getBook().getTitle();
                String comment = bookLending.getComment();
                String approvedString;
                if (bookLending.isApproved()) {
                    approvedString = "potvrdené";
                } else {
                    approvedString = "nepotvrdené";
                }
                bookLendingFxModel.setId(bookLending.getId());
                bookLendingFxModel.setBook(bookLending.getBook().getId());
                bookLendingFxModel.setTeacher(bookLending.getTeacher().getId());
                bookLendingFxModel.setYearOfReturn(yearOfReturn);
                bookLendingFxModel.setLended(lended);
                bookLendingFxModel.setReturned(returned);
                bookLendingFxModel.setBook(book.getId());
                bookLendingFxModel.setAuthor(author);
                bookLendingFxModel.setTitle(title);
                bookLendingFxModel.setComment(comment);
                bookLendingFxModel.setApprovedString(approvedString);

                bookLendingsModel.add(bookLendingFxModel);
            }

        }
    }

    public void loadLendingForAdminToModel() {
        bookLendingsModel.clear();
        for (BookLending bookLending : lendings) {

            BookLendingFxModel bookLendingFxModel = new BookLendingFxModel();

            int yearOfReturn = bookLending.getYearOfReturn();
            int lended = bookLending.getLended();
            int returned = bookLending.getReturned();
            Book book = bookLending.getBook();
            String author = bookLending.getBook().getAuthor();
            String title = bookLending.getBook().getTitle();
            String nameOfTeacher = bookLending.getTeacher().getName();
            String surnameOfTeacher = bookLending.getTeacher().getSurname();
            String comment = bookLending.getComment();
            String approvedString;
            if (bookLending.isApproved()) {
                approvedString = "potvrdené";
            } else {
                approvedString = "nepotvrdené";
            }
            
            bookLendingFxModel.setId(bookLending.getId());
            bookLendingFxModel.setBook(bookLending.getBook().getId());
            bookLendingFxModel.setTeacher(bookLending.getTeacher().getId());
            bookLendingFxModel.setYearOfReturn(yearOfReturn);
            bookLendingFxModel.setLended(lended);
            bookLendingFxModel.setReturned(returned);
            bookLendingFxModel.setBook(book.getId());
            bookLendingFxModel.setAuthor(author);
            bookLendingFxModel.setTitle(title);
            bookLendingFxModel.setNameOfTeacher(nameOfTeacher);
            bookLendingFxModel.setSurnameOfTeacher(surnameOfTeacher);
            bookLendingFxModel.setComment(comment);
            bookLendingFxModel.setApprovedString(approvedString);

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
                Book book = bookLending.getBook();
                String author = bookLending.getBook().getAuthor();
                String title = bookLending.getBook().getTitle();
                String nameOfTeacher = bookLending.getTeacher().getName();
                String surnameOfTeacher = bookLending.getTeacher().getSurname();
                String comment = bookLending.getComment();
                String approvedString;
                if (bookLending.isApproved()) {
                    approvedString = "potvrdené";
                } else {
                    approvedString = "nepotvrdené";
                }

                bookLendingFxModel.setId(bookLending.getId());
                bookLendingFxModel.setBook(bookLending.getBook().getId());
                bookLendingFxModel.setTeacher(bookLending.getTeacher().getId());
                bookLendingFxModel.setYearOfReturn(yearOfReturn);
                bookLendingFxModel.setLended(lended);
                bookLendingFxModel.setReturned(returned);
                bookLendingFxModel.setBook(book.getId());
                bookLendingFxModel.setAuthor(author);
                bookLendingFxModel.setTitle(title);
                bookLendingFxModel.setNameOfTeacher(nameOfTeacher);
                bookLendingFxModel.setSurnameOfTeacher(surnameOfTeacher);
                bookLendingFxModel.setComment(comment);
                bookLendingFxModel.setApprovedString(approvedString);

                bookLendingsModel.add(bookLendingFxModel);
            }
        }
    }

}
