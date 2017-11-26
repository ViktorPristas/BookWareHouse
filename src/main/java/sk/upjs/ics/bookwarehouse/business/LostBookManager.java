package sk.upjs.ics.bookwarehouse.business;

import sk.upjs.ics.bookwarehouse.Teacher;

public interface LostBookManager {

    void deleteAllForThisYear();

    void deleteByTeacher(Teacher teacher);

}
