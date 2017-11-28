package sk.upjs.ics.bookwarehouse.fxmodels;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.storage.BookDao;

public class BookFxModel {

    private ObservableList<Book> books;
    
//    public BookFxModel() {
//        BookDao bookDao = DaoFactory.INSTANCE.getBookDao();
//        List<Book> books = bookDao.getAll();
//        this.books = FXCollections.observableArrayList(books);
//    }

    public ObservableList<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = FXCollections.observableArrayList(books);
    }
    
    
    
}
