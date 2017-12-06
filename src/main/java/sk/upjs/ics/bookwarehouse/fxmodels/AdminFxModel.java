package sk.upjs.ics.bookwarehouse.fxmodels;

import java.util.List;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.storage.AdminDao;

public class AdminFxModel {
    
    private ObservableList<Admin> admins = new SimpleListProperty<>();
    private ObservableList<AdminFxModel> adminsModel = FXCollections.observableArrayList();
    private LongProperty id = new SimpleLongProperty();
    private StringProperty userName = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private String actualPassword;
    
    public AdminFxModel() {
        AdminDao adminDao = DaoFactory.INSTANCE.getAdminDao();
        List<Admin> admins = adminDao.getAll();
        this.admins = FXCollections.observableArrayList(admins);
    }
    
    public ObservableList<AdminFxModel> getAdminsModel() {
        return adminsModel;
    }
    
    public ObservableList<Admin> getAdmins() {
        return admins;
    }
    
    public String getActualPassword() {
        return actualPassword;
    }
    
    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
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
    
    public Admin getAdmin() {
        Admin admin = new Admin();
        admin.setId(getId());
        admin.setUserName(getUserName());
        admin.setEmail(getEmail());
        if (getPassword() == null || getPassword().equals("")) {
            admin.setPassword(getActualPassword());
        } else {
            admin.setPassword(getPassword());
        }
        return admin;
    }
    
    public void loadActualAdminToModel(Long adminId) {
        Admin a = DaoFactory.INSTANCE.getAdminDao().findById(adminId);
        setId(a.getId());
        setUserName(a.getUserName());
        setEmail(a.getEmail());
        setActualPassword(a.getPassword());
    }
    
    public void loadAdminToModel() {
        adminsModel.clear();
        for (Admin admin : admins) {
            AdminFxModel adminFxModel = new AdminFxModel();
            
            String userName = admin.getUserName();
            String email = admin.getEmail();
            
            adminFxModel.setUserName(userName);
            adminFxModel.setEmail(email);
            adminFxModel.setId(admin.getId());
            adminsModel.add(adminFxModel);
        }
    }
}
