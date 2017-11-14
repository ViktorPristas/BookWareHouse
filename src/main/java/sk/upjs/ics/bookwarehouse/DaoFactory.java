/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse;

import sk.upjs.ics.bookwarehouse.storage.MysqlLostBookDao;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.bookwarehouse.storage.MysqlAdminDao;
import sk.upjs.ics.bookwarehouse.storage.MysqlBookDao;
import sk.upjs.ics.bookwarehouse.storage.MysqlBookLendingDao;
import probablyNotUsable.MysqlBookInStockDao;
import probablyNotUsable.MysqlStockDao;
import sk.upjs.ics.bookwarehouse.storage.AdminDao;
import sk.upjs.ics.bookwarehouse.storage.BookDao;
import sk.upjs.ics.bookwarehouse.storage.BookLendingDao;
import sk.upjs.ics.bookwarehouse.storage.LostBookDao;
import sk.upjs.ics.bookwarehouse.storage.MysqlSuperAdminDao;
import sk.upjs.ics.bookwarehouse.storage.MysqlTeacherDao;
import sk.upjs.ics.bookwarehouse.storage.SuperAdminDao;
import sk.upjs.ics.bookwarehouse.storage.TeacherDao;

/**
 *
 * @author tomas
 */
public enum DaoFactory {
    INSTANCE;

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJDBCTemplate() {
        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("bwh_user");
            dataSource.setPassword("books");
            dataSource.setUrl("jdbc:mysql://localhost/BookWareHouse?serverTimezone=Europe/Bratislava");

            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    public BookDao getBookDao() {
        return new MysqlBookDao(getJDBCTemplate());
    }

    public AdminDao getAdminDao() {
        return new MysqlAdminDao(getJDBCTemplate());
    }

    public BookLendingDao getBookLendingDao() {
        return new MysqlBookLendingDao(getJDBCTemplate());
    }
    
    public SuperAdminDao getSuperAdminDao(){
        return new MysqlSuperAdminDao(getJDBCTemplate());
    }
    
    public TeacherDao getTeacherDao(){
        return new MysqlTeacherDao(getJDBCTemplate());
    }
    
    
    public LostBookDao  getLostOrDamagedBookDao(){
        return new MysqlLostBookDao(getJDBCTemplate());
    }
}