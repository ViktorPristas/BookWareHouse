package sk.upjs.ics.bookwarehouse.storage;

import java.time.LocalDateTime;


public class BookLending {
    private long id;
    private long idTeacher;
    private long idBook;
    private int time;
    private int lended;
    private int returned;
    private int lost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(long idTeacher) {
        this.idTeacher = idTeacher;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
    
    
    
    
}
