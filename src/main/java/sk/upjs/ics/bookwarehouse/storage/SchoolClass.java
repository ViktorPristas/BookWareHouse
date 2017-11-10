package sk.upjs.ics.bookwarehouse.storage;


public class SchoolClass {
    /*
    it can be a or b or aG etc.
    */ 
    private String type;
    private long idTeacher;
    private int id;
    private int year;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(long idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
    
}
