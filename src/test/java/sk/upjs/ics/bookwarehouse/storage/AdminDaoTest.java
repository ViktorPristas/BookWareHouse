package sk.upjs.ics.bookwarehouse.storage;

import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;

public class AdminDaoTest {

    AdminDao dao = DaoFactory.INSTANCE.getAdminDao();

    @Test
    public void testGetAll() {
        List<Admin> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
    }

    @Test
    public void saveNewTest() {
        List<Admin> list = dao.getAll();
        int size = list.size();
        long id = Collections.max(list, (o1, o2) -> {
            return o1.getId().compareTo(o2.getId());
        }).getId();
        id++;
        Admin admin = new Admin();
        admin.setUserName("newAdmin" + id);
        admin.setPassword("password");
        admin.setEmail("newAdmin" + id + "@gmail.com");
        admin = dao.save(admin);
        Assert.assertEquals(size + 1, dao.getAll().size());
        dao.deleteById(admin.getId());
    }

    @Test
    public void saveChangesTest() {
        Admin adminBackup = dao.getAll().get(0);
        Admin adminOld = new Admin();
        adminOld.setId(adminBackup.getId());
        adminOld.setUserName("newAdmin" + Math.floor(Math.random() * 100000));
        adminOld.setPassword("password" + Math.floor(Math.random() * 100000));
        dao.save(adminOld);
        Admin adminNew = dao.getAll().get(0);
        Assert.assertEquals(adminNew.getUserName(), adminOld.getUserName());
        Assert.assertEquals(adminNew.getPassword(), adminOld.getPassword());
        Assert.assertEquals(adminNew.getEmail(), adminOld.getEmail());
        dao.save(adminBackup);
    }

    @Test
    public void deleteByIdTest() {
        Admin admin = new Admin();
        admin.setUserName("newAdmin-1");
        admin.setPassword("password");
        admin.setEmail("newAdmin-1@gmail.com");
        admin = dao.save(admin);
        long id = admin.getId();
        dao.deleteById(id);
        List<Admin> list = dao.getAll();
        boolean b = true;
        for (Admin a : list) {
            if (a.getId().equals(id)) {
                b = false;
            }
        }
        Assert.assertTrue(b);
    }

    @Test
    public void testFindById() {
        Admin admin = new Admin();
        admin.setUserName("newAdmin-999");
        admin.setPassword("password333");
        admin.setEmail("newAdmin-444@gmail.com");
        admin = dao.save(admin);
        long id = admin.getId();
        Admin adminNew = dao.findById(id);
        Assert.assertEquals(adminNew.getUserName(), admin.getUserName());
        Assert.assertEquals(adminNew.getPassword(), admin.getPassword());
        Assert.assertEquals(adminNew.getEmail(), admin.getEmail());
        dao.deleteById(id);
    }
}
