/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse.storage;

import java.util.List;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.LostBook;

/**
 *
 * @author tomas
 */
public interface LostBookDao {

    // CREATE
    LostBook save(BookLending bookLending, Admin admin, String comment);
    
    LostBook save(LostBook lostBook);
    
     // READ
    List<LostBook> getAll();

    List<LostBook> getByYear(int year);
    
    //UPDATE
    //DONT WANT TO UPDATE 
   
     // DELETE
    boolean deleteById(long id);

   }
