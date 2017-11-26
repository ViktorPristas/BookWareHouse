package sk.upjs.ics.bookwarehouse.business;

import java.util.List;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookEdit;

public interface BookEditManager {

    List<BookEdit> findByBook(Book book);

    List<BookEdit> findByBookAndYear(Book book, int year);

}
