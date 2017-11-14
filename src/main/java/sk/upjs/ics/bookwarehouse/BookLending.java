package sk.upjs.ics.bookwarehouse;

import java.time.LocalDateTime;

public class BookLending {

    private long id;
    private Teacher teacher;
    private Book book;
    private int yearOfReturn;
    private int lended;
    private int returned;
    private int lost;
    private String comment;
    private boolean approved;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getYearOfReturn() {
        return yearOfReturn;
    }

    public void setYearOfReturn(int yearOfReturn) {
        this.yearOfReturn = yearOfReturn;
    }

    public int getLended() {
        return lended;
    }

    public void setLended(int lended) {
        this.lended = lended;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    
    

}
