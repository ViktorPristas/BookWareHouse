package sk.upjs.ics.bookwarehouse.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.DaoFactory;

public class MysqlAdminDao implements AdminDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlAdminDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Admin save(Admin admin) {
        if (admin == null) {
            return admin;
        }
        if (admin.getId() == null) { //INSERT
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("admin");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("userName", "email", "password");
            Map<String, Object> data = new HashMap<>();
            data.put("userName", admin.getUserName());
            data.put("email", admin.getUserName());
            data.put("password", admin.getUserName());
            admin.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
        } else {    // UPDATE
            String sql = "UPDATE admin SET username = ?, email = ?, password = ? WHERE id = " + admin.getId();
            jdbcTemplate.update(sql, admin.getUserName(), admin.getEmail(), admin.getPassword());
        }
        return admin;
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

    @Override
    public Admin findById(Long id) {
        List<Admin> list = DaoFactory.INSTANCE.getAdminDao().getAll();
        for (Admin admin : list) {
            if (admin.getId().equals(id)) {
                return admin;
            }
        }
        return null;
    }
}
