package sk.upjs.ics.bookwarehouse;

public class Book {
    private Long id;
    private String title;
    private String author;
    private int yearOfPublication;
    private String schoolClass;
    private int numberInStock;
    private int numberOfUsed;
    
    public int getNumberOfUsed() {
        return numberOfUsed;
    }

    public void setNumberOfUsed(int numberOfUsed) {
        this.numberOfUsed = numberOfUsed;
    }
    
    //BE AWARE- IN DATABASE THE NAME OF THIS COLUMN IS isUsed AND IT IS TINYINT
    private boolean used;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    
    
}
