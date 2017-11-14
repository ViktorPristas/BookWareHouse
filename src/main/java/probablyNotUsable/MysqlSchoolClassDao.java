/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probablyNotUsable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import probablyNotUsable.SchoolClass;

/**
 *
 * @author tomas
 */
public class MysqlSchoolClassDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlSchoolClassDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SchoolClass> getAll() {
        String sql = "SELECT id, year, idTeacher, type FROM BookWareHouse.SchoolClass;";
        List<SchoolClass> schoolClasses = jdbcTemplate.query(sql, new RowMapper<SchoolClass>() {
            @Override
            public SchoolClass mapRow(ResultSet rs, int i) throws SQLException {
                SchoolClass sc = new SchoolClass();
                sc.setId(rs.getLong("id"));
                sc.setYear(rs.getInt("year"));
                sc.setIdTeacher(rs.getLong("idTeacher"));
                sc.setType(rs.getString("type"));
                return sc;
            }
        });

        return schoolClasses;
    }

}
