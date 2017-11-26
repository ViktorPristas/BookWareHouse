package sk.upjs.ics.bookwarehouse.storage;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.LostBook;

public class LostBookDaoTest {

    LostBookDao dao = DaoFactory.INSTANCE.getLostBookDao();

    @Test
    public void GetAllTest() {

        List<LostBook> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
    }

    @Test
    public void saveNewTest() {
        List<LostBook> list = dao.getAll();
        int size = list.size();
        LostBook lostBook = new LostBook();
        lostBook.setTitle("title");
        lostBook.setAuthor("author");
        lostBook.setYearOfPublication(2004);
        lostBook.setSchoolClass("3G");
        lostBook.setNumber(31);
        lostBook.setIdTeacher(1);
        lostBook.setNameOfTeacher("nameOfTeacher");
        lostBook.setSurnameOfTeacher("surnameOfTeacher");
        lostBook.setIdAdmin(1);
        lostBook.setDate(LocalDateTime.now());
        lostBook.setComment("comment");
        lostBook.setUsernameOfAdmin("userNameOfAdmin");

        lostBook = dao.save(lostBook);
        list = dao.getAll();
        Assert.assertEquals(size + 1, list.size());
        dao.deleteById(lostBook.getId());
    }

    @Test
    public void deleteByIdTest() {
        LostBook lostBook = new LostBook();
        lostBook.setTitle("title");
        lostBook.setAuthor("author");
        lostBook.setYearOfPublication(2004);
        lostBook.setSchoolClass("7");
        lostBook.setNumber(31);
        lostBook.setIdTeacher(1);
        lostBook.setNameOfTeacher("nameOfTeacher");
        lostBook.setSurnameOfTeacher("surnameOfTeacher");
        lostBook.setIdAdmin(1);
        lostBook.setDate(LocalDateTime.now());
        lostBook.setComment("comment");
        lostBook.setUsernameOfAdmin("userNameOfAdmin");
        lostBook = dao.save(lostBook);
        long id = lostBook.getId();
        dao.deleteById(id);
        List<LostBook> list = dao.getAll();
        boolean b = true;
        for (LostBook lb : list) {
            if (lb.getId().equals(id)) {
                b = false;
            }
        }
        Assert.assertTrue(b);
    }

}
