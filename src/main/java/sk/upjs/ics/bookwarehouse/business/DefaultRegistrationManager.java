package sk.upjs.ics.bookwarehouse.business;

import java.util.List;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.SuperAdmin;
import sk.upjs.ics.bookwarehouse.Teacher;

public class DefaultRegistrationManager {

    public static boolean isNewTeacherEmail(String email) {
        List<Teacher> list = DaoFactory.INSTANCE.getTeacherDao().getAll();
        for (Teacher t : list) {
            if (t.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNewAdminEmail(String email) {
        List<Admin> list = DaoFactory.INSTANCE.getAdminDao().getAll();
        for (Admin a : list) {
            if (a.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    //when teacher username is the email
    public static boolean isNewUserName(String userName) {
        List<Teacher> teachers = DaoFactory.INSTANCE.getTeacherDao().getAll();
        for (Teacher teacher : teachers) {
            if (teacher.getEmail().equals(userName)) {
                return false;
            }
        }
        List<Admin> admins = DaoFactory.INSTANCE.getAdminDao().getAll();
        for (Admin admin : admins) {
            if (admin.getUserName().equals(userName)) {
                return false;
            }
        }
        List<SuperAdmin> superAdmins = DaoFactory.INSTANCE.getSuperAdminDao().getAll();
        for (SuperAdmin sa : superAdmins) {
            if (sa.getUserName().equals(userName)) {
                return false;
            }
        }

        return true;
    }
}
