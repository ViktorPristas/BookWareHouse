package sk.upjs.ics.bookwarehouse.business;


public interface PasswordManager {

    String hashPassword(String passwordForHash);

    boolean isCorrectPassword(String passwordToCheck, String hashedPassword);
    
}
