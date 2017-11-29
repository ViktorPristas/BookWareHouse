package sk.upjs.ics.bookwarehouse.business;

import java.util.List;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;

public class RegistrationManager {

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
}
