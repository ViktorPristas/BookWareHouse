package sk.upjs.ics.bookwarehouse.business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class SHA256PasswordManager implements PasswordManager {

    @Override
    public String hashPassword(String passwordForHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(passwordForHash.getBytes());
            byte[] data = md.digest();
            return DatatypeConverter.printHexBinary(data);

        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isCorrectPassword(String passwordToCheck, String hashedPassword) {
        return (hashPassword(passwordToCheck).equals(hashedPassword));
    }
}
