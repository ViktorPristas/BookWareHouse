/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse.storage;

import java.util.List;
import sk.upjs.ics.bookwarehouse.Teacher;

/**
 *
 * @author tomas
 */
public interface TeacherDao {

    //CREATE
    void save(Teacher teacher);

    //READ
    List<Teacher> getAll();

    public Teacher findById(long id);

    public Teacher findByEmail(String email);

    // DELETE
    boolean deleteById(long id);
}
