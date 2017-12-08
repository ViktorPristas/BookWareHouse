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

public class UserIdentificationManagerTest {

    private TeacherDao teacherDao = DaoFactory.INSTANCE.getTeacherDao();
    private AdminDao adminDao = DaoFactory.INSTANCE.getAdminDao();
    private SuperAdminDao superAdminDao = DaoFactory.INSTANCE.getSuperAdminDao();

    @Test
    public void setUserTest() {
        String uname1 = "email-222@gmail.com";
        String uname2 = "email-223@gmail.com";
        String uname3 = "email-224@gmail.com";

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

        //superadmin
        UserIdentificationManager.setUser(uname1);
        Assert.assertEquals(UserIdentificationManager.getTypeOfUser(), 3);
        Assert.assertEquals(UserIdentificationManager.getId().longValue(), idSA);

        //admin
        UserIdentificationManager.setUser(uname2);
        Assert.assertEquals(UserIdentificationManager.getTypeOfUser(), 2);
        Assert.assertEquals(UserIdentificationManager.getId().longValue(), idA);

        //teacher
        UserIdentificationManager.setUser(uname3);
        Assert.assertEquals(UserIdentificationManager.getTypeOfUser(), 1);
        Assert.assertEquals(UserIdentificationManager.getId().longValue(), idT);

        superAdminDao.deleteById(idSA);
        adminDao.deleteById(idA);
        teacherDao.deleteById(idT);
    }

    @Test
    public void logOutTest() {
        String uname1 = "email-222@gmail.com";

        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setUserName(uname1);
        superAdmin.setPassword("password");
        superAdmin = superAdminDao.save(superAdmin);
        long idSA = superAdmin.getId();

        UserIdentificationManager.setUser(uname1);
        UserIdentificationManager.logOut();
        Assert.assertEquals(UserIdentificationManager.getTypeOfUser(), 0);
        Assert.assertEquals(UserIdentificationManager.getId(), null);

        superAdminDao.deleteById(idSA);
    }
}
