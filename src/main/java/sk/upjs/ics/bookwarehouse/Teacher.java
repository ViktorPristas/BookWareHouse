package sk.upjs.ics.bookwarehouse;

public class Teacher {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int numberOfStudentsInClass;

    public int getNumberOfStudentsInClass() {
        return numberOfStudentsInClass;
    }

    public void setNumberOfStudentsInClass(int numberOfStudentsInClass) {
        this.numberOfStudentsInClass = numberOfStudentsInClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public void setId(){
        this.id = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public boolean hasClass() {
        if (numberOfStudentsInClass > 0) {
            return true;
        }
        return false;
    }

}
