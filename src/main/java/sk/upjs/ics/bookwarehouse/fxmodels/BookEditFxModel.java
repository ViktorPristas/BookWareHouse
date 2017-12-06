/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse.fxmodels;

import java.time.LocalDateTime;
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
import sk.upjs.ics.bookwarehouse.BookEdit;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;
import sk.upjs.ics.bookwarehouse.storage.BookEditDao;

/**
 *
 * @author tomas
 */
public class BookEditFxModel {
    
    private ObservableList<BookEdit> bookEdits = new SimpleListProperty<>();
    private ObservableList<BookEditFxModel> bookEditsModel = FXCollections.observableArrayList();
    private LongProperty id = new SimpleLongProperty();
    private StringProperty author = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty schoolClass = new SimpleStringProperty();
    private StringProperty nameOfAdmin = new SimpleStringProperty();
    private StringProperty date = new SimpleStringProperty();
    private IntegerProperty numberBefore = new SimpleIntegerProperty();
    private IntegerProperty numberAfter = new SimpleIntegerProperty();
    //private StringProperty comment = new SimpleStringProperty("");

    public BookEditFxModel() {
        BookEditDao bookEditDao = DaoFactory.INSTANCE.getBookEditDao();
        List<BookEdit> bookEdits = bookEditDao.getAll();
        this.bookEdits = FXCollections.observableArrayList(bookEdits);
    }
    
    public ObservableList<BookEditFxModel> getBookEditsModel() {
        return bookEditsModel;
    }
    
    public ObservableList<BookEdit> getBookEdits() {
        return bookEdits;
    }
    
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

    public Integer getNumberBefore() {
        return numberBefore.get();
    }

    public IntegerProperty numberBeforeProperty() {
        return numberBefore;
    }
    
    public void setNumberBefore(Integer numberBefore) {
        this.numberBefore.set(numberBefore);
    }

    public IntegerProperty numberAfterProperty() {
        return numberAfter;
    }

    public Integer getNumberAfter() {
        return numberAfter.get();
    }
    
    public void setNumberAfter(Integer numberAfter) {
        this.numberAfter.set(numberAfter);
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
    
    public void loadBooksToModel() {
        bookEditsModel.clear();
        for (BookEdit bookEdit : bookEdits){
            BookEditFxModel bookEditFxModel = new BookEditFxModel();
            
            Long id = bookEdit.getId();
            String author = bookEdit.getBook().getAuthor();
            String title = bookEdit.getBook().getTitle();
            String schoolClass = bookEdit.getBook().getSchoolClass();
            String nameOfAdmin = bookEdit.getNameOfAdmin();
            LocalDateTime date = bookEdit.getDate();
            int numberBefore = bookEdit.getNumberBefore();
            int numberAfter = bookEdit.getNumberAfter();
            
            bookEditFxModel.setId(id);
            bookEditFxModel.setAuthor(author);
            bookEditFxModel.setTitle(title);
            bookEditFxModel.setSchoolClass(schoolClass);
            bookEditFxModel.setNameOfAdmin(nameOfAdmin);
            bookEditFxModel.setDate(date);
            bookEditFxModel.setNumberBefore(numberBefore); System.out.println("before: " + numberBefore);
            bookEditFxModel.setNumberAfter(numberAfter); System.out.println("after: " + numberAfter);
            bookEditsModel.add(bookEditFxModel);
        }
    }
    
}
