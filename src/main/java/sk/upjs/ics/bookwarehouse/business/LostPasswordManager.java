package sk.upjs.ics.bookwarehouse.business;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import sk.upjs.ics.bookwarehouse.DaoFactory;
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
        String to = email;
        
        String host = "gmail.com";
        final String user = "noreplyBookWareHouse@gmail.com";
        final String password = "SilneHeslo123";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Nové heslo");
            
            String newPassword = java.util.UUID.randomUUID().toString().substring(0, 10);
            TeacherDao dao = DaoFactory.INSTANCE.getTeacherDao();
            Teacher teacher = dao.findByEmail(email);
            teacher.setPassword(newPassword);
            
            message.setText(newPassword);
            
            Transport.send(message);
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        return true;
    }
}
