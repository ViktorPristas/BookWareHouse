package sk.upjs.ics.bookwarehouse.storage;

import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.DaoFactory;

public class BookDaoTest {

    BookDao dao = DaoFactory.INSTANCE.getBookDao();

    @Test
    public void testGetAll() {
        List<Book> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
    }

    @Test
    public void saveNewTest() {
        List<Book> list = dao.getAll();
        int size = list.size();
        long id = Collections.max(list, (o1, o2) -> {
            return o1.getId().compareTo(o2.getId());
        }).getId();
        id++;
        Book b = new Book();
        b.setAuthor("author");
        b.setTitle("title" + id);
        b.setYearOfPublication(2012);
        b.setSchoolClass("7");
        b.setNumberInStock(21);
        b.setNumberOfUsed(17);
        b.setComment("comment");
        b.setUsed(false);
        dao.save(b);

        Assert.assertEquals(size + 1, dao.getAll().size());
        dao.deleteById(b.getId());
    }

    @Test
    public void saveChangesTest() {
        Book bookBackup = dao.getAll().get(0);
        Book bookOld = new Book();
        bookOld.setId(bookBackup.getId());
        bookOld.setAuthor("author");
        bookOld.setTitle("title-23");
        bookOld.setYearOfPublication(2012);
        bookOld.setSchoolClass("8");
        bookOld.setNumberInStock(21);
        bookOld.setNumberOfUsed(17);
        bookOld.setComment("comment");
        bookOld.setUsed(false);
        dao.save(bookOld);
        Book bookNew = dao.getAll().get(0);
        Assert.assertEquals(bookOld.getAuthor(), bookNew.getAuthor());
        Assert.assertEquals(bookOld.getTitle(), bookNew.getTitle());
        Assert.assertEquals(bookOld.getYearOfPublication(), bookNew.getYearOfPublication());
        Assert.assertEquals(bookOld.getSchoolClass(), bookNew.getSchoolClass());
        Assert.assertEquals(bookOld.getNumberInStock(), bookNew.getNumberInStock());
        Assert.assertEquals(bookOld.getNumberOfUsed(), bookNew.getNumberOfUsed());
        Assert.assertEquals(bookOld.getComment(), bookNew.getComment());
        Assert.assertEquals(bookOld.isUsed(), bookNew.isUsed());
        dao.save(bookBackup);
    }

    @Test
    public void deleteByIdTest() {
        Book b = new Book();
        b.setAuthor("author");
        b.setTitle("title" + b.getId());
        b.setYearOfPublication(2012);
        b.setSchoolClass("7");
        b.setNumberInStock(21);
        b.setNumberOfUsed(17);
        b.setComment("comment");
        b.setUsed(false);

        b = dao.save(b);
        long id = b.getId();
        dao.deleteById(id);
        List<Book> list = dao.getAll();
        boolean bool = true;
        for (Book a : list) {
            if (a.getId().equals(id)) {
                bool = false;
            }
        }
        Assert.assertTrue(bool);
    }

    @Test
    public void testFindById() {
        Book b = new Book();
        b.setAuthor("author333");
        b.setTitle("title333");
        b.setYearOfPublication(2010);
        b.setSchoolClass("9");
        b.setNumberInStock(31);
        b.setNumberOfUsed(12);
        b.setComment("comment333");
        b.setUsed(false);
        b = dao.save(b);
        long id = b.getId();

        Book bookNew = dao.findById(id);
        Assert.assertEquals(b.getAuthor(), bookNew.getAuthor());
        Assert.assertEquals(b.getTitle(), bookNew.getTitle());
        Assert.assertEquals(b.getYearOfPublication(), bookNew.getYearOfPublication());
        Assert.assertEquals(b.getSchoolClass(), bookNew.getSchoolClass());
        Assert.assertEquals(b.getNumberInStock(), bookNew.getNumberInStock());
        Assert.assertEquals(b.getNumberOfUsed(), bookNew.getNumberOfUsed());
        Assert.assertEquals(b.getComment(), bookNew.getComment());
        Assert.assertEquals(b.isUsed(), bookNew.isUsed());
        dao.deleteById(id);

    }
}
