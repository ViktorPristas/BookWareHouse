package sk.upjs.ics.bookwarehouse;

import java.time.LocalDateTime;


public class BookEdit {
   private Long id;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
   private Book book;
   private String nameOfAdmin;
   private LocalDateTime date;
   private int numberBefore;
   private int numberAfter;
   private String comment;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getNameOfAdmin() {
        return nameOfAdmin;
    }

    public void setNameOfAdmin(String nameOfAdmin) {
        this.nameOfAdmin = nameOfAdmin;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getNumberBefore() {
        return numberBefore;
    }

    public void setNumberBefore(int numberBefore) {
        this.numberBefore = numberBefore;
    }

    public int getNumberAfter() {
        return numberAfter;
    }

    public void setNumberAfter(int numberAfter) {
        this.numberAfter = numberAfter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
   
   public void setId(){
       this.id = null;
   }
    
}
