package sk.upjs.ics.bookwarehouse;

public class Teacher {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    /**
     * 3,4.. when primary school or 3G, 4G when gymnasium
     *
     */
    private String yearOfSchoolClass;

    /**
     * A or B or nothing when there is just one class
     *
     */
    private String nameOfSchoolClass;
    private int numberOfStudentsInClass;

    public String getYearOfSchoolClass() {
        return yearOfSchoolClass;
    }

    public void setYearOfSchoolClass(String yearOfSchoolClass) {
        this.yearOfSchoolClass = yearOfSchoolClass;
    }

    public String getNameOfSchoolClass() {
        return nameOfSchoolClass;
    }

    public void setNameOfSchoolClass(String nameOfSchoolClass) {
        this.nameOfSchoolClass = nameOfSchoolClass;
    }

    public int getNumberOfStudentsInClass() {
        return numberOfStudentsInClass;
    }

    public void setNumberOfStudentsInClass(int numberOfStudentsInClass) {
        this.numberOfStudentsInClass = numberOfStudentsInClass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    }

    public boolean hasClass() {
        if (numberOfStudentsInClass > 0) {
            return true;
        }
        return false;
    }

}
