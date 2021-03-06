package sk.upjs.ics.bookwarehouse.business;

import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookEdit;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.storage.BookEditDao;

public class DefaultBookEditManager implements BookEditManager {

    private BookEditDao bookEditDao = DaoFactory.INSTANCE.getBookEditDao();

    @Override
    public List<BookEdit> findByBook(Book book) {
        List<BookEdit> bookEdits = bookEditDao.getAll();
        List<BookEdit> list = new ArrayList<>();
        for (BookEdit bookEdit : bookEdits) {
            if (bookEdit.getBook().getId().equals(book.getId())) {
                list.add(bookEdit);
            }
        }
        return list;
    }

    @Override
    public List<BookEdit> findByBookAndYear(Book book, int year) {
        if (year == 0) {
            return findByBook(book);
        }
        List<BookEdit> bookEdits = bookEditDao.getAll();
        List<BookEdit> list = new ArrayList<>();
        for (BookEdit bookEdit : bookEdits) {
            if (bookEdit.getBook().getId().equals(book.getId()) || bookEdit.getDate().getYear() == year) {
                list.add(bookEdit);
            }
        }
        return list;
    }

}
