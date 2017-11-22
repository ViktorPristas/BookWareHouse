/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse.storage;

import java.util.List;
import sk.upjs.ics.bookwarehouse.BookEdit;

public interface BookEditDao {

    // CREATE AND UPDATE
    BookEdit save(BookEdit bookEdit);
    
     // READ
    List<BookEdit> getAll();
    
    // DELETE
    boolean deleteById(long id);
    
}
