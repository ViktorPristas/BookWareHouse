package sk.upjs.ics.bookwarehouse.fxmodels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.business.UserIdentificationManager;
import sk.upjs.ics.bookwarehouse.storage.TeacherDao;

public class TeacherFxModel {

    private ObservableList<Teacher> teachers = new SimpleListProperty<>();
    private ObservableList<TeacherFxModel> teachersModel = FXCollections.observableArrayList();
    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private IntegerProperty numberOfStudentsInClass = new SimpleIntegerProperty();
    private String actualPassword;
    private TeacherDao teacherDao = DaoFactory.INSTANCE.getTeacherDao();

    public TeacherFxModel() {
        List<Teacher> teachers = teacherDao.getAll();
        this.teachers = FXCollections.observableArrayList(teachers);

    }

    public ObservableList<TeacherFxModel> getTeachersModel() {
        return teachersModel;
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public IntegerProperty numberOfStudentsInClassProperty() {
        return numberOfStudentsInClass;
    }

    public int getNumberOfStudentsInClass() {
        return numberOfStudentsInClass.get();
    }

    public void setNumberOfStudentsInClass(int numberOfStudentsInClass) {
        this.numberOfStudentsInClass.set(numberOfStudentsInClass);
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
        t.setId(getId());
        t.setName(getName());
        t.setSurname(getSurname());
        t.setEmail(getEmail());
        if (UserIdentificationManager.getId() != null) {
            t.setId(UserIdentificationManager.getId());
        }
        if (getPassword() == null || getPassword().equals("")) {
            t.setPassword(getActualPassword());
        } else {
            t.setPassword(getPassword());
        }
        return t;
    }

    public void loadActualTeacherToModel(Long teacherId) {
        Teacher t = DaoFactory.INSTANCE.getTeacherDao().findById(teacherId);

        setId(t.getId());
        setName(t.getName());
        setSurname(t.getSurname());
        setEmail(t.getEmail());
        setNumberOfStudentsInClass(getNumberOfStudentsInClass());
        setActualPassword(t.getPassword());
    }

    public List<String> getTeacherList() {
        List<String> nameOfTeachers = new ArrayList<>();
        Collections.sort(teachers, new Comparator<Teacher>() {
            @Override
            public int compare(Teacher t1, Teacher t2) {
                return t1.getSurname().compareTo(t2.getSurname());
            }
        });
        for (Teacher teacher : teachers) {
            String nameAndSurname = teacher.getName() + " " + teacher.getSurname();
            nameOfTeachers.add(nameAndSurname);
        }
        return nameOfTeachers;
    }

    public void loadTeacherToModel() {
        teachersModel.clear();
        List<Teacher> teachers = teacherDao.getAll();
        this.teachers = FXCollections.observableArrayList(teachers);
        for (Teacher teacher : this.teachers) {
            TeacherFxModel teacherFxModel = new TeacherFxModel();

            Long id = teacher.getId();
            String name = teacher.getName();
            String surname = teacher.getSurname();
            String email = teacher.getEmail();
            int numberOfStudentsInClass = teacher.getNumberOfStudentsInClass();

            teacherFxModel.setId(id);
            teacherFxModel.setName(name);
            teacherFxModel.setSurname(surname);
            teacherFxModel.setEmail(email);
            teacherFxModel.setNumberOfStudentsInClass(numberOfStudentsInClass);

            teachersModel.add(teacherFxModel);
        }
    }
}
