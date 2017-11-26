package sk.upjs.ics.bookwarehouse.storage;

import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;

public class TeacherDaoTest {

    TeacherDao dao = DaoFactory.INSTANCE.getTeacherDao();

    @Test
    public void testGetAll() {
        List<Teacher> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
    }

    @Test
    public void saveNewTest() {
        List<Teacher> list = dao.getAll();
        int size = list.size();
        long id = Collections.max(list, (o1, o2) -> {
            return o1.getId().compareTo(o2.getId());
        }).getId();
        Teacher t = new Teacher();
        t.setName("name" + id);
        t.setSurname("surname" + id);
        t.setEmail("email" + id + "@gmail.com");
        t.setPassword("password");
        int n = (int) (Math.random() * 100);
        t.setNumberOfStudentsInClass(n);
        t = dao.save(t);

        Assert.assertEquals(size + 1, dao.getAll().size());
        dao.deleteById(t.getId());
    }

    @Test
    public void saveChangesTest() {
        Teacher teacherBackup = dao.getAll().get(0);

        Teacher teacherOld = new Teacher();
        teacherOld.setId(teacherBackup.getId());
        double id = Math.floor(Math.random() * 100000);
        teacherOld.setName("name" + id);
        teacherOld.setSurname("surname" + id);
        teacherOld.setEmail("teacher" + id + "@gmail.com");
        teacherOld.setPassword("password");
        int n = (int) (Math.random() * 100);
        teacherOld.setNumberOfStudentsInClass(n);

        dao.save(teacherOld);
        Teacher teacherNew = dao.getAll().get(0);

        Assert.assertEquals(teacherOld.getName(), teacherNew.getName());
        Assert.assertEquals(teacherOld.getSurname(), teacherNew.getSurname());
        Assert.assertEquals(teacherOld.getEmail(), teacherNew.getEmail());
        Assert.assertEquals(teacherOld.getPassword(), teacherNew.getPassword());
        Assert.assertEquals(teacherOld.getNumberOfStudentsInClass(), teacherNew.getNumberOfStudentsInClass());
        dao.save(teacherBackup);
    }

    @Test
    public void deleteByIdTest() {
        Teacher t = new Teacher();
        t.setName("name-1");
        t.setSurname("surname-1");
        t.setEmail("email-2@gmail.com");
        t.setPassword("password");
        t = dao.save(t);
        long id = t.getId();

        System.out.println(id);
        dao.deleteById(id);
        List<Teacher> list = dao.getAll();
        boolean b = true;
        for (Teacher teacher : list) {
            if (teacher.getId().equals(id)) {
                b = false;
            }
        }
        //DONT WANT TO DELETE TEACHER WITH BOOKLENDINGS
        List<BookLending> bookLendings = DaoFactory.INSTANCE.getBookLendingDao().getAll();
        for (BookLending bl : bookLendings) {
            if (bl.getBook().getId().equals(id)) {
                b = true;
            }
        }
        Assert.assertTrue(b);
    }

}
