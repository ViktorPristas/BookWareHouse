package sk.upjs.ics.bookwarehouse.business;

public enum ManagerFactory {
    INSTANCE;

    PasswordManager getPasswordManager() {
        return new SHA256PasswordManager();
    }

    LostBookManager getLostBookManager() {
        return new DefaultLostBookManager();
    }

    BookEditManager getBookEditManager() {
        return new DefaultBookEditManager();
    }
}
