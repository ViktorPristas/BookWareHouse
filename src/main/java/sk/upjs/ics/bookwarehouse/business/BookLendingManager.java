package sk.upjs.ics.bookwarehouse.business;

import sk.upjs.ics.bookwarehouse.Teacher;


public interface BookLendingManager {

    void deleteAllForThisYear();

    void deleteByTeacher(Teacher teacher);
    
}
