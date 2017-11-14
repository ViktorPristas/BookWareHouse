package sk.upjs.ics.bookwarehouse.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.bookwarehouse.SuperAdmin;

public class MysqlSuperAdminDao implements SuperAdminDao {

    private JdbcTemplate jdbcTemplate;
    
    public MysqlSuperAdminDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<SuperAdmin> getAll() {
        String sql = "SELECT id, userName, password FROM BookWareHouse.SuperAdmin;";
        List<SuperAdmin> superAdmins = jdbcTemplate.query(sql, new RowMapper<SuperAdmin>() {
            @Override
            public SuperAdmin mapRow(ResultSet rs, int i) throws SQLException {
                SuperAdmin sa = new SuperAdmin();
                sa.setId(rs.getLong("id"));
                sa.setUsername(rs.getString("userName"));
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
}
