package sk.upjs.ics.bookwarehouse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LostBook {

    private Long id;
    private long idBook;
    private String title;
    private String author;
    private int yearOfPublication;
    private String schoolClass;
    private int number;
    private long idTeacher;
    private String nameOfTeacher;
    private String surnameOfTeacher;
    private long idAdmin;
    private String usernameOfAdmin;
    private LocalDateTime date;
    private String comment;
    
    public LostBook() {
        
    }
    
    public LostBook(BookLending bookLending, Admin admin, String comment) {
        setIdBook(bookLending.getBook().getId());
        setTitle(bookLending.getBook().getTitle());
        setAuthor(bookLending.getBook().getAuthor());
        setYearOfPublication(bookLending.getBook().getYearOfPublication());
        setSchoolClass(bookLending.getBook().getSchoolClass());
        setNumber(bookLending.getLost());
        setIdTeacher(bookLending.getTeacher().getId());
        setNameOfTeacher(bookLending.getTeacher().getName());
        setSurnameOfTeacher(bookLending.getTeacher().getSurname());
        setIdAdmin(admin.getId());
        setUsernameOfAdmin(admin.getUserName());
        setDate(LocalDateTime.now());
        setComment(comment);
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getIdBook() {
        return idBook;
    }
    
    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getYearOfPublication() {
        return yearOfPublication;
    }
    
    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
    
    public String getSchoolClass() {
        return schoolClass;
    }
    
    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public long getIdTeacher() {
        return idTeacher;
    }
    
    public void setIdTeacher(long idTeacher) {
        this.idTeacher = idTeacher;
    }
    
    public String getNameOfTeacher() {
        return nameOfTeacher;
    }
    
    public void setNameOfTeacher(String nameOfTeacher) {
        this.nameOfTeacher = nameOfTeacher;
    }
    
    public String getSurnameOfTeacher() {
        return surnameOfTeacher;
    }
    
    public void setSurnameOfTeacher(String surnameOfTeacher) {
        this.surnameOfTeacher = surnameOfTeacher;
    }
    
    public long getIdAdmin() {
        return idAdmin;
    }
    
    public void setIdAdmin(long idAdmin) {
        this.idAdmin = idAdmin;
    }
    
    public String getUsernameOfAdmin() {
        return usernameOfAdmin;
    }
    
    public void setUsernameOfAdmin(String usernameOfAdmin) {
        this.usernameOfAdmin = usernameOfAdmin;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
