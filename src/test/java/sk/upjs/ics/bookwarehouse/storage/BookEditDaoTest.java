package sk.upjs.ics.bookwarehouse.storage;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.BookEdit;
import sk.upjs.ics.bookwarehouse.DaoFactory;

public class BookEditDaoTest {

    BookEditDao dao = DaoFactory.INSTANCE.getBookEditDao();

    @Test
    public void saveNewTest() {
        List<BookEdit> list = dao.getAll();
        int size = list.size();
        long id = 1;
        if (size > 0) {
            id = Collections.max(list, (o1, o2) -> {
                return o1.getId().compareTo(o2.getId());
            }).getId();
            id++;
        }
        BookEdit bookEdit = new BookEdit();
        bookEdit.setBook(DaoFactory.INSTANCE.getBookDao().getAll().get(0));
        bookEdit.setDate(LocalDateTime.now());
        bookEdit.setComment("comment");
        bookEdit.setNameOfAdmin("admin" + id);
        bookEdit.setNumberBefore(17);
        bookEdit.setNumberAfter(21);
        bookEdit = dao.save(bookEdit);
        Assert.assertEquals(size + 1, dao.getAll().size());
        dao.deleteById(bookEdit.getId());
    }

    @Test
    public void testGetAll() {
        List<BookEdit> list = dao.getAll();
        int size = list.size();
        long id = 1;
        if (size > 0) {
            id = Collections.max(list, (o1, o2) -> {
                return o1.getId().compareTo(o2.getId());
            }).getId();
            id++;
        }
        BookEdit bookEdit = new BookEdit();
        bookEdit.setBook(DaoFactory.INSTANCE.getBookDao().getAll().get(0));
        bookEdit.setDate(LocalDateTime.now());
        bookEdit.setComment("comment");
        bookEdit.setNameOfAdmin("admin" + id);
        bookEdit.setNumberBefore(17);
        bookEdit.setNumberAfter(21);
        dao.save(bookEdit);

        list = dao.getAll();
        if (list != null) {
            assertTrue(list.size() > 0);
        }
    }

    @Test
    public void deleteByIdTest() {
        BookEdit bookEdit = new BookEdit();
        bookEdit.setBook(DaoFactory.INSTANCE.getBookDao().getAll().get(0));
        bookEdit.setDate(LocalDateTime.now());
        bookEdit.setComment("comment");
        bookEdit.setNameOfAdmin("admin");
        bookEdit.setNumberBefore(17);
        bookEdit.setNumberAfter(21);
        bookEdit = dao.save(bookEdit);
        long id = bookEdit.getId();
        dao.deleteById(id);
        List<BookEdit> list = dao.getAll();
        boolean b = true;
        for (BookEdit be : list) {
            if (be.getId().equals(id)) {
                b = false;
            }
        }
        Assert.assertTrue(b);
    }
}
