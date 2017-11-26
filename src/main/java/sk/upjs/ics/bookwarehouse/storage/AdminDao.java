/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse.storage;

import java.util.List;
import sk.upjs.ics.bookwarehouse.Admin;

/**
 *
 * @author tomas
 */
public interface AdminDao {

    //CREATE
    Admin save(Admin admin);

    // DELETE
    boolean deleteById(long id);

    List<Admin> getAll();

}
