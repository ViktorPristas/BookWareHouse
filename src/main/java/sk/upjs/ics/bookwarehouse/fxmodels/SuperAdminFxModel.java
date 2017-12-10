package sk.upjs.ics.bookwarehouse.fxmodels;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.SuperAdmin;


public class SuperAdminFxModel {
    private LongProperty id = new SimpleLongProperty(); 
    private StringProperty userName = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private String actualPassword;

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

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }
    
    
    
    public SuperAdmin getSuperAdmin(){
        SuperAdmin sa = new SuperAdmin();
        sa.setId(getId());
        sa.setUserName(getUserName());
        if (getPassword() == null || getPassword().equals("")) {
            sa.setPassword(getActualPassword());
        } else {
            sa.setPassword(getPassword());
        }        
        return sa;
    }
    
    public void loadActualSuperAdminToModel(Long superAdminId) {
        SuperAdmin sa = DaoFactory.INSTANCE.getSuperAdminDao().findById(superAdminId);
        setId(sa.getId());
        setUserName(sa.getUserName());
        setActualPassword(sa.getPassword());
    }
    
}
