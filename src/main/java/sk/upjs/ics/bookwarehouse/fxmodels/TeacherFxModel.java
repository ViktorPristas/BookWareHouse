package sk.upjs.ics.bookwarehouse.fxmodels;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.ics.bookwarehouse.Teacher;

public class TeacherFxModel {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private int numberOfStudentsInClass;

    public int getNumberOfStudentsInClass() {
        return numberOfStudentsInClass;
    }

    public void setNumberOfStudentsInClass(int numberOfStudentsInClass) {
        this.numberOfStudentsInClass = numberOfStudentsInClass;
    }

    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public Teacher getTeacher() {
        Teacher t = new Teacher();
        t.setName(getName());
        t.setSurname(getSurname());
        t.setEmail(getEmail());
        t.setPassword(getPassword());
        t.setId(getId());
        return t;
    }

}
