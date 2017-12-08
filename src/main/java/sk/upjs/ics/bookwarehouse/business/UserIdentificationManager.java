package sk.upjs.ics.bookwarehouse.business;

import java.util.List;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.SuperAdmin;
import sk.upjs.ics.bookwarehouse.Teacher;

public class UserIdentificationManager {

    private static int typeOfUser;
    private static Long id;

    public static int getTypeOfUser() {
        return typeOfUser;
    }

    public static Long getId() {
        return id;
    }

    //Teacher is 1
    //Admin is 2
    //SuperAdmin is 3
    
    public static boolean setUser(String userName) {
        List<Teacher> teachers = DaoFactory.INSTANCE.getTeacherDao().getAll();
        for (Teacher teacher : teachers) {
            if (teacher.getEmail().equals(userName)) {
                UserIdentificationManager.typeOfUser = 1;
                UserIdentificationManager.id = teacher.getId();
                return true;
            }
        }
        List<Admin> admins = DaoFactory.INSTANCE.getAdminDao().getAll();
        for (Admin admin : admins) {
            if (admin.getUserName().equals(userName)) {
                UserIdentificationManager.typeOfUser = 2;
                UserIdentificationManager.id = admin.getId();
                return true;
            }
        }
        List<SuperAdmin> superAdmins = DaoFactory.INSTANCE.getSuperAdminDao().getAll();
        for (SuperAdmin sa : superAdmins) {
            if (sa.getUserName().equals(userName)) {
                UserIdentificationManager.typeOfUser = 3;
                UserIdentificationManager.id = sa.getId();
                return true;
            }
        }
        return false;
    }

    public static void logOut() {
        UserIdentificationManager.typeOfUser = 0;
        UserIdentificationManager.id = null;
    }

}
