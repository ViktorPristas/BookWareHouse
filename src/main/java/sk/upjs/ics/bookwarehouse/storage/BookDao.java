/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse.storage;

import java.util.List;
import sk.upjs.ics.bookwarehouse.Book;

/**
 *
 * @author tomas
 */
public interface BookDao {

    //CREATE
    Book save(Book book);

    //READ
    Book findById(long id);

    List<Book> getAll();

    // DELETE
    boolean deleteById(long id);

}
