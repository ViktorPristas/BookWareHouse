package sk.upjs.ics.bookwarehouse.business;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.storage.BookLendingDao;
import sk.upjs.ics.bookwarehouse.storage.TeacherDao;

public class DefaultBookLendingXLSManager {

    private static BookLendingDao dao = DaoFactory.INSTANCE.getBookLendingDao();

    public static void exportMyBookLendingsToXls(Teacher teacher) {
        List<BookLending> list1 = dao.getAll();
        List<BookLending> list = new ArrayList<>();
        for (BookLending bookLending : list1) {
            if (bookLending.getTeacher().getId().equals(teacher.getId())) {
                list.add(bookLending);
            }
        }

        try (PrintWriter pw = new PrintWriter(new File("mojeknihy.xls"))) {
            pw.print("Nazov");
            pw.print("\t");
            pw.print("Autor");
            pw.print("\t");
            pw.print("Rocnik");
            pw.print("\t");
            pw.print("Pocet pozicanych");
            pw.print("\t");
            pw.print("Pocet vratenych");
            pw.print("\n");
            for (BookLending bookLending : list) {
                pw.print(bookLending.getBook().getTitle());
                pw.print("\t");
                pw.print(bookLending.getBook().getAuthor());
                pw.print("\t");
                pw.print(bookLending.getBook().getSchoolClass());
                pw.print("\t");
                pw.print(bookLending.getLended());
                pw.print("\t");
                pw.print(bookLending.getReturned());
                pw.print("\n");
            }
        } catch (Exception e) {
        }
        try {
            Desktop.getDesktop().open(new File("mojeknihy.xls"));
        } catch (IOException ex) {
            Logger.getLogger(DefaultBookLendingXLSManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /* public static void main(String[] args) {
        Teacher t = DaoFactory.INSTANCE.getTeacherDao().findById(72);
        DefaultBookLendingXLSManager.exportMyBookLendingsToXls(t);
    }*/
}
