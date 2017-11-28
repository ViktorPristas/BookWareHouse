package sk.upjs.ics.bookwarehouse.fxmodels;

import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.storage.BookDao;

public class BookFxModel {

    private ObservableList<Book> books;
    private LongProperty id;
    private StringProperty title;
    private StringProperty author;
    private int yearOfPublication;
    private StringProperty schoolClass;
    private int numberInStock;
    private int numberOfUsed;
    private boolean used;
    private StringProperty comment;

    public BookFxModel() {
        BookDao bookDao = DaoFactory.INSTANCE.getBookDao();
        List<Book> books = bookDao.getAll();
        this.books = FXCollections.observableArrayList(books);
    }
    
    
    public ObservableList<Book> getBooks() {
        return books;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty CommentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public Long getId() {
        return id.get();
    }

    public LongProperty IdProperty() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty TitleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty AuthorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getSchoolClass() {
        return schoolClass.get();
    }

    public StringProperty SchoolClassProperty() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass.set(schoolClass);
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }

    public int getNumberOfUsed() {
        return numberOfUsed;
    }

    public void setNumberOfUsed(int numberOfUsed) {
        this.numberOfUsed = numberOfUsed;
    }

    public Book getBook() {
        Book b = new Book();
        b.setId(getId());
        b.setAuthor(getAuthor());
        b.setComment(getComment());
        b.setTitle(getTitle());
        b.setYearOfPublication(getYearOfPublication());
        b.setSchoolClass(getSchoolClass());
        b.setNumberInStock(getNumberInStock());
        b.setNumberOfUsed(getNumberOfUsed());
        b.setUsed(isUsed());
        return b;
    }

}
