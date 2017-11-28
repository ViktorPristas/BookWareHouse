package sk.upjs.ics.bookwarehouse.business;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookEdit;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.ManagerFactory;
import sk.upjs.ics.bookwarehouse.storage.BookEditDao;

public class BookEditManagerTest {

    BookEditManager manager = ManagerFactory.INSTANCE.getBookEditManager();

    @Test
    public void findByBookTest() {
        BookEditDao dao = DaoFactory.INSTANCE.getBookEditDao();

        BookEdit bookEdit = new BookEdit();

        Book b = new Book();
        b.setAuthor("author-321");
        b.setTitle("title-123");
        b.setYearOfPublication(2012);
        b.setSchoolClass("7");
        b.setNumberInStock(21);
        b.setNumberOfUsed(17);
        b.setComment("comment");
        b.setUsed(true);
        b = DaoFactory.INSTANCE.getBookDao().save(b);

        bookEdit.setBook(b);
        bookEdit.setDate(LocalDateTime.now());
        bookEdit.setComment("comment");
        bookEdit.setNameOfAdmin("admin-" + 12);
        bookEdit.setNumberBefore(17);
        bookEdit.setNumberAfter(21);
        bookEdit = dao.save(bookEdit);

        List<BookEdit> list = manager.findByBook(b);
        Long id = b.getId();
        for (BookEdit be : list) {
            Assert.assertEquals(be.getBook().getId(), id);
        }

        DaoFactory.INSTANCE.getBookDao().deleteById(b.getId());
        dao.deleteById(bookEdit.getId());
        
    }

    public void findByBookAndYearTest(){
        BookEditDao dao = DaoFactory.INSTANCE.getBookEditDao();
        int year = 2017;

        BookEdit bookEdit = new BookEdit();

        Book b = new Book();
        b.setAuthor("author-321");
        b.setTitle("title-123");
        b.setYearOfPublication(2012);
        b.setSchoolClass("7");
        b.setNumberInStock(21);
        b.setNumberOfUsed(17);
        b.setComment("comment");
        b.setUsed(true);
        b = DaoFactory.INSTANCE.getBookDao().save(b);

        bookEdit.setBook(b);
        bookEdit.setDate(LocalDateTime.now());
        bookEdit.setComment("comment");
        bookEdit.setNameOfAdmin("admin-" + 12);
        bookEdit.setNumberBefore(17);
        bookEdit.setNumberAfter(21);
        bookEdit = dao.save(bookEdit);

        List<BookEdit> list = manager.findByBookAndYear(b, year);
        Long id = b.getId();
        for (BookEdit be : list) {
            Assert.assertEquals(be.getBook().getId(), id);
            Assert.assertEquals(be.getDate().getYear(), year);
        }

        DaoFactory.INSTANCE.getBookDao().deleteById(b.getId());
        dao.deleteById(bookEdit.getId());
        
    }
}
