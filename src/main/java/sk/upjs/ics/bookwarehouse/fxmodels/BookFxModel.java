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
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.storage.BookDao;

public class BookFxModel {

    private ObservableList<Book> books = new SimpleListProperty<>();
    private ObservableList<BookFxModel> booksmodel = FXCollections.observableArrayList();
    private LongProperty id = new SimpleLongProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty author = new SimpleStringProperty();
    private IntegerProperty yearOfPublication = new SimpleIntegerProperty();
    private StringProperty schoolClass = new SimpleStringProperty();
    private IntegerProperty numberInStock = new SimpleIntegerProperty();
    private IntegerProperty numberOfUsed = new SimpleIntegerProperty();
    private BooleanProperty used = new SimpleBooleanProperty();
    private StringProperty comment = new SimpleStringProperty();
    private int numberInStockBefore;
    private StringProperty isUsedString = new SimpleStringProperty();
    private  BookDao bookDao = DaoFactory.INSTANCE.getBookDao();
       

    public BookFxModel() {
        List<Book> books = bookDao.getAll();
        this.books = FXCollections.observableArrayList(books);
    }

    public StringProperty isUsedStringProperty() {
        return isUsedString;
    }

    public String getIsUsedString() {
        return isUsedString.get();
    }

    public void setIsUsedString(String isUsedString) {
        this.isUsedString.set(isUsedString);
    }

    public ObservableList<BookFxModel> getBooksModel() {
        return booksmodel;
    }

    public int getNumberInStockBefore() {
        return numberInStockBefore;
    }

    public void setNumberInStockBefore(int numberInStockBefore) {
        this.numberInStockBefore = numberInStockBefore;
    }

    public ObservableList<Book> getBooks() {
        return books;
    }

    public boolean isUsed() {
        return used.get();
    }

    public BooleanProperty isUsedProperty() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used.set(used);
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
        return yearOfPublication.get();
    }

    public IntegerProperty yearOfPublicationProperty() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication.set(yearOfPublication);
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
        return numberInStock.get();
    }

    public IntegerProperty numberInStockProperty() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock.set(numberInStock);
    }

    public IntegerProperty numberOfUsedProperty() {
        return numberOfUsed;
    }

    public int getNumberOfUsed() {
        return numberOfUsed.get();
    }

    public void setNumberOfUsed(int numberOfUsed) {
        this.numberOfUsed.set(numberOfUsed);
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

    public void loadBooksToModel() {
        booksmodel.clear();
        List<Book> books = bookDao.getAll();
        this.books = FXCollections.observableArrayList(books);
       
          for (Book book : this.books) {
            BookFxModel bookFxModel = new BookFxModel();

            String title = book.getTitle();
            String author = book.getAuthor();
            int yearOfPublication = book.getYearOfPublication();
            String schoolClass = book.getSchoolClass();
            boolean isUsed = book.isUsed();
            int numberInStock = book.getNumberInStock();
            int numberOfUsed = book.getNumberOfUsed();
            String isUsedString;
            if (book.isUsed()) {
                isUsedString = "áno";
            } else {
                isUsedString = "nie";
            }

            bookFxModel.setId(book.getId());
            bookFxModel.setTitle(title);
            bookFxModel.setAuthor(author);
            bookFxModel.setYearOfPublication(yearOfPublication);
            bookFxModel.setSchoolClass(schoolClass);
            bookFxModel.setUsed(isUsed);
            bookFxModel.setNumberInStock(numberInStock);
            bookFxModel.setNumberOfUsed(numberOfUsed);
            bookFxModel.setNumberInStockBefore(numberInStock);
            bookFxModel.setIsUsedString(isUsedString);
            booksmodel.add(bookFxModel);
        }

    }

    public void loadFilteredBooksToModel(String selectedSchoolClass) {
        booksmodel.clear();
        for (Book book : books) {
            if (book.getSchoolClass().equals(selectedSchoolClass) || selectedSchoolClass.equals("<Všetko>")) {
                BookFxModel bookFxModel = new BookFxModel();

                String title = book.getTitle();
                String author = book.getAuthor();
                int yearOfPublication = book.getYearOfPublication();
                String schoolClass = book.getSchoolClass();
                int numberInStock = book.getNumberInStock();
                int numberOfUsed = book.getNumberOfUsed();
                String isUsedString;
                if (book.isUsed()) {
                    isUsedString = "áno";
                } else {
                    isUsedString = "nie";
                }

                bookFxModel.setId(book.getId());
                bookFxModel.setTitle(title);
                bookFxModel.setAuthor(author);
                bookFxModel.setYearOfPublication(yearOfPublication);
                bookFxModel.setSchoolClass(schoolClass);
                bookFxModel.setNumberInStock(numberInStock);
                bookFxModel.setNumberOfUsed(numberOfUsed);
                bookFxModel.setNumberInStockBefore(numberInStock);
                bookFxModel.setIsUsedString(isUsedString);
                booksmodel.add(bookFxModel);
            }
        }

    }

}
