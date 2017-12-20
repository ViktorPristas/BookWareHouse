package sk.upjs.ics.bookwarehouse.business;

import java.util.List;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.Teacher;

public interface BookLendingManager {

    void deleteAllForThisYear();

    void deleteByTeacher(Teacher teacher);

    void deleteAllForYear(int year);

    List<BookLending> myBookLendings(Teacher teacher);
}
