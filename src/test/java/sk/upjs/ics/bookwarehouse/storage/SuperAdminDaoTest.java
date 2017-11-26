package sk.upjs.ics.bookwarehouse.storage;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.SuperAdmin;

public class SuperAdminDaoTest {

    SuperAdminDao dao;

    public SuperAdminDaoTest() {
        dao = DaoFactory.INSTANCE.getSuperAdminDao();
    }

    @Test
    public void saveNewTest() {
        List<SuperAdmin> list = dao.getAll();
        int size = list.size();
        long id = Collections.max(list, (o1, o2) -> {
            return o1.getId().compareTo(o2.getId());
        }).getId();
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setUserName("newAdmin" + id);
        superAdmin.setPassword("password");
        superAdmin = dao.save(superAdmin);
        Assert.assertEquals(size + 1, dao.getAll().size());
        dao.deleteById(superAdmin.getId());
    }

    @Test
    public void saveChangesTest() {
        SuperAdmin superAdminBackup = dao.getAll().get(0);
        SuperAdmin superAdminOld = new SuperAdmin();
        superAdminOld.setId(superAdminBackup.getId());
        superAdminOld.setUserName("newAdmin" + Math.floor(Math.random() * 1000));
        superAdminOld.setPassword("password" + Math.floor(Math.random() * 1000));
        dao.save(superAdminOld);
        SuperAdmin superAdminNew = dao.getAll().get(0);
        Assert.assertEquals(superAdminNew.getUserName(), superAdminOld.getUserName());
        Assert.assertEquals(superAdminNew.getPassword(), superAdminOld.getPassword());
        dao.save(superAdminBackup);
    }

    @Test
    public void getAllTest() {
        List<SuperAdmin> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
    }

    @Test
    public void deleteByIdTest() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setUserName("newAdmin01");
        superAdmin.setPassword("password");
        superAdmin = dao.save(superAdmin);
        long id = superAdmin.getId();
        dao.deleteById(id);
        List<SuperAdmin> list = dao.getAll();
        boolean b = true;
        for (SuperAdmin sa : list) {
            if (sa.getId().equals(id)) {
                b = false;
            }
        }
        Assert.assertTrue(b);
    }

}
