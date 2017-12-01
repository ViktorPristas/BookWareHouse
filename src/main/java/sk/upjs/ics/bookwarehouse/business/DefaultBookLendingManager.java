package sk.upjs.ics.bookwarehouse.business;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.storage.BookLendingDao;

public class DefaultBookLendingManager implements BookLendingManager {

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
        deleteAllForYear(LocalDateTime.now().getYear());
    }

    @Override
    public void deleteAllForYear(int year) {
        List<BookLending> bookLendings = bookLendingDao.getAll();
        for (BookLending bookLending : bookLendings) {
            if (bookLending.getYearOfReturn() == year) {
                bookLendingDao.deleteById(bookLending.getId());
            }
        }
    }

    @Override
    public List<BookLending> myBookLendings(Teacher teacher) {
        List<BookLending> list = bookLendingDao.getAll();
        List<BookLending> myList = new ArrayList<>();
        for (BookLending bookLending : list) {
            if (bookLending.getTeacher().getId().equals(teacher.getId())) {
                myList.add(bookLending);
            }
        }
        return myList;
    }

}
