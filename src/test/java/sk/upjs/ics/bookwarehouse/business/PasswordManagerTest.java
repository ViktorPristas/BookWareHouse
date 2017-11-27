package sk.upjs.ics.bookwarehouse.business;

import org.junit.Assert;
import org.junit.Test;
import sk.upjs.ics.bookwarehouse.ManagerFactory;

public class PasswordManagerTest {

    PasswordManager manager = ManagerFactory.INSTANCE.getPasswordManager();

    @Test
    public void testLength() {
        String password = "abracadabra";
        String hashedString = manager.hashPassword(password);
        Assert.assertEquals(hashedString.length(), 64);
    }

    @Test
    public void testPassword() {
        String password1 = "abracadabra12";
        String password2 = "abracadabra21";
        String hashedString1 = manager.hashPassword(password1);
        String hashedString3 = manager.hashPassword(password1);
        String hashedString2 = manager.hashPassword(password2);

        Assert.assertEquals(hashedString1, hashedString3);
        Assert.assertNotEquals(hashedString1, hashedString2);
    }
}
