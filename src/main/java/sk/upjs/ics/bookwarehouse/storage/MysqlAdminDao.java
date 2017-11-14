/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bookwarehouse.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.Book;

public class MysqlAdminDao implements AdminDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlAdminDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Admin admin) {
        if (admin == null) {
            return false;
        }
        
        return true;
    }

    @Override
    public List<Admin> getAll() {
        String sql = "SELECT id, userName, email, password FROM BookWareHouse.Admin;";
        List<Admin> admins = jdbcTemplate.query(sql, new RowMapper<Admin>() {

            @Override
            public Admin mapRow(ResultSet rs, int i) throws SQLException {
                Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setUserName(rs.getString("userName"));
                a.setEmail("email");
                a.setPassword(rs.getString("password"));
                return a;
            }
        });
        return admins;
    }

    // DELETE
    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM BookWareHouse.Admin WHERE id = " + id;
        try {
            int deleted = jdbcTemplate.update(sql);
            return deleted == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
