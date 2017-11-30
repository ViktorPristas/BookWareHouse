package sk.upjs.ics.bookwarehouse.business;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.ManagerFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.storage.TeacherDao;

// prepared with the help of https://www.javatpoint.com/example-of-sending-email-using-java-mail-api
public class LostPasswordManager {

    public static boolean sendNewPassword(String email) {

        //CHECKING IF THE EMAIL IS VALID 
        boolean isValidEmail = false;
        TeacherDao teacherDao = DaoFactory.INSTANCE.getTeacherDao();
        List<Teacher> list = teacherDao.getAll();
        for (Teacher t : list) {
            if (t.getEmail().equals(email)) {
                isValidEmail = true;
            }
        }
        if (!isValidEmail) {
            return false;
        }

        String USER_NAME = "noreplyBookWareHouse";  // GMail user name (just the part before "@gmail.com")
        String PASSWORD = "SilneHeslo123"; // GMail password
        String RECIPIENT = email;

        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = {RECIPIENT}; // list of recipient email addresses
        String subject = "Nové heslo";
        String newPassword = java.util.UUID.randomUUID().toString().substring(0, 10);
        TeacherDao dao = DaoFactory.INSTANCE.getTeacherDao();
        Teacher teacher = dao.findByEmail(email);
        if (teacher != null) {
            teacher.setPassword(newPassword);
            dao.save(teacher);
            teacher = dao.findByEmail(email);
            System.out.println(ManagerFactory.INSTANCE.getPasswordManager().hashPassword(newPassword));
            System.out.println(teacher.getPassword());
            sendFromGMail(from, pass, to, subject, newPassword, email);
            return true;
        }
        System.out.println("nieco je zle");
        return false;

    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body, String email) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();

        }
    }
}
