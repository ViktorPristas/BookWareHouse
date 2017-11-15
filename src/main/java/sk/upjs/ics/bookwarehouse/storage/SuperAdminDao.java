/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse.storage;

import java.util.List;
import sk.upjs.ics.bookwarehouse.SuperAdmin;

/**
 *
 * @author tomas
 */
public interface SuperAdminDao {

    //CREATE
    void save(SuperAdmin superAdmin);
    
    //READ
    List<SuperAdmin> getAll();
    
    // DELETE
    boolean deleteById(long id);

    }
