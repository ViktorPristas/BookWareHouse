package sk.upjs.ics.bookwarehouse;

public class Admin {

    private Long id;
    private String userName;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
//this.password = ManagerFactory.INSTANCE.getPasswordManager().hashPassword(password);
    }
    
    public void setId(){
        this.id = null;
    }

}
