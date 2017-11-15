/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse.storage;

import java.util.List;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.Teacher;

/**
 *
 * @author tomas
 */
public interface BookLendingDao {

    //CREATE
    void save(BookLending bookLending);
    
    void save(Book book, Teacher teacher, int lended, String comment);
    
    void save(Book book, Teacher teacher, int lended, String comment, int yearOfReturn);
    

    //READ   
    List<BookLending> getAll();

    // DELETE
    boolean deleteById(long id);

}
