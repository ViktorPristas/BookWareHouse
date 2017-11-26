package sk.upjs.ics.bookwarehouse.business;

import java.time.LocalDateTime;
import java.util.List;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.storage.BookLendingDao;

public class DefaultLostBookManager implements LostBookManager {

    private BookLendingDao bookLendingDao = DaoFactory.INSTANCE.getBookLendingDao();

    @Override
    public void deleteByTeacher(Teacher teacher) {
        List<BookLending> bookLendings = bookLendingDao.getAll();
        for (BookLending bookLending : bookLendings) {
            if (bookLending.getTeacher().getId() == teacher.getId()) {
                bookLendingDao.deleteById(bookLending.getId());
            }
        }
    }

    @Override
    public void deleteAllForThisYear() {
        List<BookLending> bookLendings = bookLendingDao.getAll();
        for (BookLending bookLending : bookLendings) {
            if (bookLending.getYearOfReturn() == LocalDateTime.now().getYear()) {
                bookLendingDao.deleteById(bookLending.getId());
            }
        }
    }
}
