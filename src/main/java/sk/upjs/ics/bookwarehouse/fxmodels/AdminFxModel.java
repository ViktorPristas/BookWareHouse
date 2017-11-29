package sk.upjs.ics.bookwarehouse.fxmodels;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.ics.bookwarehouse.Admin;


public class AdminFxModel {
    
    private LongProperty id = new SimpleLongProperty();
    private StringProperty userName = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    
    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }
    
    public void setId(long id) {
        this.id.set(id);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName.set(userName);
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

    public Admin getAdmin(){
        Admin admin = new Admin();
        admin.setId(getId());
        admin.setUserName(getUserName());
        admin.setPassword(getPassword());
        admin.setEmail(getEmail());
        return admin;
    }
    

    
}
