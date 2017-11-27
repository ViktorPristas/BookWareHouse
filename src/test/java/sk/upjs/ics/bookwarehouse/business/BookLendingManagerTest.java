package sk.upjs.ics.bookwarehouse.business;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.ManagerFactory;
import sk.upjs.ics.bookwarehouse.Teacher;

public class BookLendingManagerTest {

    BookLendingManager manager = ManagerFactory.INSTANCE.getBookLendingManager();

    @Test
    public void deleteAllForYear() {
        int year = 2063;

        BookLending bl = new BookLending();
        bl.setYearOfReturn(year);
        bl.setLended(21);
        bl.setReturned(0);
        bl.setLost(0);
        bl.setComment("comment");
        bl.setApproved(false);

        Teacher t = new Teacher();
        t.setName("name-1");
        t.setSurname("surname-1");
        t.setEmail("email-3@gmail.com");
        t.setPassword("password");
        t = DaoFactory.INSTANCE.getTeacherDao().save(t);
        bl.setTeacher(t);

        Book b = new Book();
        b.setAuthor("author");
        b.setTitle("title" + b.getId());
        b.setYearOfPublication(2012);
        b.setSchoolClass("7");
        b.setNumberInStock(21);
        b.setNumberOfUsed(17);
        b.setComment("comment");
        b.setUsed(true);
        b = DaoFactory.INSTANCE.getBookDao().save(b);
        bl.setBook(b);

        BookLending bl1 = new BookLending();
        bl1.setYearOfReturn(year);
        bl1.setLended(21);
        bl1.setReturned(0);
        bl1.setLost(0);
        bl1.setComment("comment");
        bl1.setApproved(false);

        Teacher t1 = new Teacher();
        t1.setName("name-2");
        t1.setSurname("surname-2");
        t1.setEmail("email-4@gmail.com");
        t1.setPassword("password");
        t1 = DaoFactory.INSTANCE.getTeacherDao().save(t);
        bl1.setTeacher(t1);

        Book b1 = new Book();
        b1.setAuthor("author1");
        b1.setTitle("title1" + b.getId());
        b1.setYearOfPublication(2012);
        b1.setSchoolClass("7");
        b1.setNumberInStock(21);
        b1.setNumberOfUsed(17);
        b1.setComment("comment");
        b1.setUsed(true);
        b1 = DaoFactory.INSTANCE.getBookDao().save(b);
        bl1.setBook(b1);

        manager.deleteAllForYear(year);
        List<BookLending> list = DaoFactory.INSTANCE.getBookLendingDao().getAll();

        for (BookLending bookL : list) {
            Assert.assertNotEquals(bookL.getYearOfReturn(), year);
        }

        DaoFactory.INSTANCE.getBookDao().deleteById(b.getId());
        DaoFactory.INSTANCE.getTeacherDao().deleteById(t.getId());
        DaoFactory.INSTANCE.getBookDao().deleteById(b1.getId());
        DaoFactory.INSTANCE.getTeacherDao().deleteById(t1.getId());
    }

    @Test
    public void deleteByTeacher() {
        BookLending bl = new BookLending();
        bl.setYearOfReturn(2020);
        bl.setLended(21);
        bl.setReturned(0);
        bl.setLost(0);
        bl.setComment("comment");
        bl.setApproved(false);

        Teacher t = new Teacher();
        t.setName("name-1");
        t.setSurname("surname-1");
        t.setEmail("email-3@gmail.com");
        t.setPassword("password");
        t = DaoFactory.INSTANCE.getTeacherDao().save(t);
        bl.setTeacher(t);

        Book b = new Book();
        b.setAuthor("author");
        b.setTitle("title" + b.getId());
        b.setYearOfPublication(2012);
        b.setSchoolClass("7");
        b.setNumberInStock(21);
        b.setNumberOfUsed(17);
        b.setComment("comment");
        b.setUsed(true);
        b = DaoFactory.INSTANCE.getBookDao().save(b);
        bl.setBook(b);

        BookLending bl1 = new BookLending();
        bl1.setYearOfReturn(2021);
        bl1.setLended(21);
        bl1.setReturned(0);
        bl1.setLost(0);
        bl1.setComment("comment");
        bl1.setApproved(false);

        bl1.setTeacher(t);

        Book b1 = new Book();
        b1.setAuthor("author1");
        b1.setTitle("title1" + b.getId());
        b1.setYearOfPublication(2012);
        b1.setSchoolClass("7");
        b1.setNumberInStock(21);
        b1.setNumberOfUsed(17);
        b1.setComment("comment");
        b1.setUsed(true);
        b1 = DaoFactory.INSTANCE.getBookDao().save(b);
        bl1.setBook(b1);

        manager.deleteByTeacher(t);
        List<BookLending> list = DaoFactory.INSTANCE.getBookLendingDao().getAll();

        for (BookLending bookL : list) {
            Assert.assertNotEquals(bookL.getTeacher().getId(), t.getId());
        }

        DaoFactory.INSTANCE.getBookDao().deleteById(b.getId());
        DaoFactory.INSTANCE.getTeacherDao().deleteById(t.getId());
        DaoFactory.INSTANCE.getBookDao().deleteById(b1.getId());
    }
}
