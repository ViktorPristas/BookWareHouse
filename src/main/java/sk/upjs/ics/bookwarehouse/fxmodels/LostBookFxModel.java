package sk.upjs.ics.bookwarehouse.fxmodels;

import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LostBookFxModel {

    private LongProperty id = new SimpleLongProperty();
    //book
    //private LongProperty idBook = new SimpleLongProperty();
    private StringProperty author = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty schoolClass = new SimpleStringProperty();
    //private IntegerProperty yearOfPublication = new SimpleIntegerProperty();
    //admin
    private StringProperty nameOfAdmin = new SimpleStringProperty();

    //teacher
    //private LongProperty idTeacher = new SimpleLongProperty();
    private StringProperty nameOfTeacher = new SimpleStringProperty();
    private StringProperty surnameOfTeacher = new SimpleStringProperty();
    //others
    private StringProperty date = new SimpleStringProperty();
    private IntegerProperty number = new SimpleIntegerProperty();
    //private StringProperty comment = new SimpleStringProperty("");

    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getSchoolClass() {
        return schoolClass.get();
    }

    public StringProperty schoolClassProperty() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass.set(schoolClass);
    }

    public StringProperty nameOfAdminProperty() {
        return nameOfAdmin;
    }

    public String getNameOfAdmin() {
        return nameOfAdmin.get();
    }

    public void setNameOfAdmin(String nameOfAdmin) {
        this.nameOfAdmin.set(nameOfAdmin);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public LocalDateTime getDate() {
        return LocalDateTime.parse(date.toString());
    }

    public void setDate(LocalDateTime date) {
        this.date.set(date.toString());
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public Integer getNumber() {
        return number.get();
    }

    public void setNumberAfter(Integer number) {
        this.number.set(number);
    }

    // ASI NEBUDE
    /*public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment.set(comment);
    }*/
    public String getNameOfTeacher() {
        return nameOfTeacher.get();
    }

    public StringProperty nameOfTeacherProperty() {
        return nameOfTeacher;
    }

    public void setNameOfTeacher(String nameOfTeacher) {
        this.nameOfTeacher.set(nameOfTeacher);
    }

    public StringProperty surnameOfTeacherProperty() {
        return surnameOfTeacher;
    }

    public String getSurnameOfTeacher() {
        return surnameOfTeacher.get();
    }

    public void setSurnameOfTeacher(String surnameOfTeacher) {
        this.surnameOfTeacher.set(surnameOfTeacher);
    }

}
