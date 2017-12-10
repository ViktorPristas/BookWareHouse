package sk.upjs.ics.bookwarehouse.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.SuperAdmin;

public class MysqlSuperAdminDao implements SuperAdminDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlSuperAdminDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //CREATE
    @Override
    public SuperAdmin save(SuperAdmin superAdmin) {
        if (superAdmin == null) {
            return null;
        }
        if (superAdmin.getId() == null) { //INSERT
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("SuperAdmin");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("userName", "password");
            Map<String, Object> data = new HashMap<>();
            data.put("userName", superAdmin.getUserName());
            data.put("password", superAdmin.getPassword());
            superAdmin.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
        } else {    // UPDATE
            String sql = "UPDATE SuperAdmin SET userName = ?, password = ?  WHERE id = " + superAdmin.getId();
            jdbcTemplate.update(sql, superAdmin.getUserName(), superAdmin.getPassword());
        }
        return superAdmin;
    }

    //READ
    @Override
    public List<SuperAdmin> getAll() {
        String sql = "SELECT id, userName, password FROM BookWareHouse.SuperAdmin;";
        List<SuperAdmin> superAdmins = jdbcTemplate.query(sql, new RowMapper<SuperAdmin>() {
            @Override
            public SuperAdmin mapRow(ResultSet rs, int i) throws SQLException {
                SuperAdmin sa = new SuperAdmin();
                sa.setId(rs.getLong("id"));
                sa.setUserName(rs.getString("userName"));
                sa.setPassword(rs.getString("password"));
                return sa;
            }

        });
        return superAdmins;
    }

    // DELETE
    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM BookWareHouse.SuperAdmin WHERE id = " + id;
        try {
            int deleted = jdbcTemplate.update(sql);
            return deleted == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public SuperAdmin findById(Long id) {
        List<SuperAdmin> list = DaoFactory.INSTANCE.getSuperAdminDao().getAll();
        for(SuperAdmin superAdmin : list) {
            if (superAdmin.getId().equals(id)) {
                return superAdmin;
            }
        }
        return null;
    }

}
