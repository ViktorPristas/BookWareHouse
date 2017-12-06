package sk.upjs.ics.bookwarehouse.storage;

import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;

public class BookLendingDaoTest {

    BookLendingDao dao = DaoFactory.INSTANCE.getBookLendingDao();

    @Test
    public void testGetAll() {
        List<BookLending> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
    }

    @Test
    public void saveNewTest() {
        List<BookLending> list = dao.getAll();
        int size = list.size();
        long id = Collections.max(list, (o1, o2) -> {
            return o1.getId().compareTo(o2.getId());
        }).getId();
        id++;
        BookLending bl = new BookLending();
        bl.setYearOfReturn(2018);
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

        bl = dao.save(bl);
        Assert.assertEquals(size + 1, dao.getAll().size());
        dao.deleteById(bl.getId());
        DaoFactory.INSTANCE.getBookDao().deleteById(b.getId());
        DaoFactory.INSTANCE.getTeacherDao().deleteById(t.getId());
    }

    @Test
    public void saveChangesTest() {
        BookLending bookLendingBackup = dao.getAll().get(0);
        BookLending bookLendingOld = new BookLending();
        bookLendingOld.setId(bookLendingBackup.getId());
        bookLendingOld.setYearOfReturn(2018);
        bookLendingOld.setLended(21);
        bookLendingOld.setReturned(0);
        bookLendingOld.setLost(0);
        bookLendingOld.setComment("comment");
        bookLendingOld.setApproved(false);
        bookLendingOld.setTeacher(bookLendingBackup.getTeacher());
        bookLendingOld.setBook(bookLendingBackup.getBook());

        dao.save(bookLendingOld);
        BookLending bookLendingNew = dao.getAll().get(0);

        Assert.assertEquals(bookLendingOld.getId(), bookLendingNew.getId());
        Assert.assertEquals(bookLendingOld.getYearOfReturn(), bookLendingNew.getYearOfReturn());
        Assert.assertEquals(bookLendingOld.getLended(), bookLendingNew.getLended());
        Assert.assertEquals(bookLendingOld.getReturned(), bookLendingNew.getReturned());
        Assert.assertEquals(bookLendingOld.getLost(), bookLendingNew.getLost());
        Assert.assertEquals(bookLendingOld.getComment(), bookLendingNew.getComment());
        Assert.assertEquals(bookLendingOld.getTeacher().getId(), bookLendingNew.getTeacher().getId());
        Assert.assertEquals(bookLendingOld.getBook().getId(), bookLendingNew.getBook().getId());
        Assert.assertEquals(bookLendingOld.isApproved(), bookLendingNew.isApproved());

        dao.save(bookLendingBackup);
    }

    @Test
    public void deleteByIdTest() {
        BookLending bl = new BookLending();
        bl.setYearOfReturn(2018);
        bl.setLended(21);
        bl.setReturned(0);
        bl.setLost(0);
        bl.setComment("comment");
        bl.setApproved(true);

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

        bl = dao.save(bl);
        long id = bl.getId();

        // System.out.println(id);
        dao.deleteById(id);
        List<BookLending> list = dao.getAll();
        boolean bool = true;
        for (BookLending bookl : list) {
            if (bookl.getId().equals(id)) {
                bool = false;
            }
        }
        DaoFactory.INSTANCE.getBookDao().deleteById(b.getId());
        DaoFactory.INSTANCE.getTeacherDao().deleteById(t.getId());
    }

    public void testFindById() {
        BookLending bl = new BookLending();
        bl.setYearOfReturn(2018);
        bl.setLended(21);
        bl.setReturned(0);
        bl.setLost(0);
        bl.setComment("comment");
        bl.setApproved(true);

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

        bl = dao.save(bl);
        long id = bl.getId();

        BookLending bookLendingNew = dao.findById(id);
        Assert.assertEquals(bl.getId(), bookLendingNew.getId());
        Assert.assertEquals(bl.getYearOfReturn(), bookLendingNew.getYearOfReturn());
        Assert.assertEquals(bl.getLended(), bookLendingNew.getLended());
        Assert.assertEquals(bl.getReturned(), bookLendingNew.getReturned());
        Assert.assertEquals(bl.getLost(), bookLendingNew.getLost());
        Assert.assertEquals(bl.getComment(), bookLendingNew.getComment());
        Assert.assertEquals(bl.getTeacher().getId(), bookLendingNew.getTeacher().getId());
        Assert.assertEquals(bl.getBook().getId(), bookLendingNew.getBook().getId());
        Assert.assertEquals(bl.isApproved(), bookLendingNew.isApproved());

        dao.deleteById(id);
        DaoFactory.INSTANCE.getBookDao().deleteById(b.getId());
        DaoFactory.INSTANCE.getTeacherDao().deleteById(t.getId());
    }
}
