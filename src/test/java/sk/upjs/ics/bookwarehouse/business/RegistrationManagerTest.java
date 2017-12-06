package sk.upjs.ics.bookwarehouse.business;

import org.junit.Assert;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.SuperAdmin;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.storage.AdminDao;
import sk.upjs.ics.bookwarehouse.storage.SuperAdminDao;
import sk.upjs.ics.bookwarehouse.storage.TeacherDao;

public class RegistrationManagerTest {

    private TeacherDao teacherDao = DaoFactory.INSTANCE.getTeacherDao();
    private AdminDao adminDao = DaoFactory.INSTANCE.getAdminDao();
    private SuperAdminDao superAdminDao = DaoFactory.INSTANCE.getSuperAdminDao();

    @Test
    public void isNewTeacherEmail() {
        String email1 = "email-222@gmail.com";
        String email2 = "takytoEmailNemozeExistovat";

        Teacher t = new Teacher();
        t.setName("name-1");
        t.setSurname("surname-1");
        t.setEmail(email1);
        t.setPassword("password");
        t = teacherDao.save(t);
        long id = t.getId();

        Assert.assertFalse(DefaultRegistrationManager.isNewTeacherEmail(email1));
        Assert.assertTrue(DefaultRegistrationManager.isNewTeacherEmail(email2));

        teacherDao.deleteById(id);
    }

    @Test
    public void isNewAdminEmail() {
        String email1 = "email-222@gmail.com";
        String email2 = "takytoEmailNemozeExistovat";
        
        Admin admin = new Admin();
        admin.setUserName("newAdmin-1");
        admin.setPassword("password");
        admin.setEmail(email1);
        admin = adminDao.save(admin);
        long id = admin.getId();
        
        Assert.assertFalse(DefaultRegistrationManager.isNewAdminEmail(email1));
        Assert.assertTrue(DefaultRegistrationManager.isNewAdminEmail(email2));

        adminDao.deleteById(id);
    }

    @Test
    public void isNewUserNameTest() {
        String uname1 = "email-222@gmail.com";
        String uname2 = "email-223@gmail.com";
        String uname3 = "email-224@gmail.com";
        String uname4 = "takytoEmailNemozeExistovat";

        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setUserName(uname1);
        superAdmin.setPassword("password");
        superAdmin = superAdminDao.save(superAdmin);
        long idSA = superAdmin.getId();

        
        Admin admin = new Admin();
        admin.setUserName(uname2);
        admin.setPassword("password");
        admin.setEmail("nejakyMail");
        admin = adminDao.save(admin);
        long idA = admin.getId();
        
        Teacher t = new Teacher();
        t.setName("name-1");
        t.setSurname("surname-1");
        t.setEmail(uname3);
        t.setPassword("password");
        t = teacherDao.save(t);
        long idT = t.getId();
        
        Assert.assertFalse(DefaultRegistrationManager.isNewUserName(uname1));
        Assert.assertFalse(DefaultRegistrationManager.isNewUserName(uname2));
        Assert.assertFalse(DefaultRegistrationManager.isNewUserName(uname3));
        Assert.assertTrue(DefaultRegistrationManager.isNewUserName(uname4));

        superAdminDao.deleteById(idSA);
        adminDao.deleteById(idA);
        teacherDao.deleteById(idT);
    }

}
