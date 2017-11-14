package sk.upjs.ics.bookwarehouse.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.bookwarehouse.Teacher;

public class MysqlTeacherDao implements TeacherDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public MysqlTeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Teacher> getAll() {
        String sql = "SELECT id, name, surname, email, password, yearOfSchoolClass, nameOfSchoolClass,"
                + "numberOfStudentsInClass FROM BookWareHouse.Teacher;";
        List<Teacher> teachers = jdbcTemplate.query(sql, new RowMapper<Teacher>() {
            @Override
            public Teacher mapRow(ResultSet rs, int i) throws SQLException {
                Teacher t = new Teacher();
                t.setId(rs.getLong("id"));
                t.setName(rs.getString("name"));
                t.setSurname(rs.getString("surname"));
                t.setEmail(rs.getString("email"));
                t.setPassword(rs.getString("password"));
                t.setYearOfSchoolClass(rs.getString("yearOfSchoolClass"));
                t.setNameOfSchoolClass(rs.getString("nameOfSchoolClass"));
                t.setNumberOfStudentsInClass(rs.getInt("numberOfStudentsInClass"));
                return t;
            }
        });
        return teachers;
    }
    
    @Override
    public Teacher findById(long id) {
        String sql = "SELECT id, name, surname, email, password, yearOfSchoolClass, nameOfSchoolClass,"
                + "numberOfStudentsInClass FROM BookWareHouse.Teacher WHERE id = " + id;
        List<Teacher> teachers = jdbcTemplate.query(sql, new RowMapper<Teacher>() {
            @Override
            public Teacher mapRow(ResultSet rs, int i) throws SQLException {
                Teacher t = new Teacher();
                t.setId(id);
                t.setName(rs.getString("name"));
                t.setSurname(rs.getString("surname"));
                t.setEmail(rs.getString("email"));
                t.setPassword(rs.getString("password"));
                t.setYearOfSchoolClass(rs.getString("yearOfSchoolClass"));
                t.setNameOfSchoolClass(rs.getString("nameOfSchoolClass"));
                t.setNumberOfStudentsInClass(rs.getInt("numberOfStudentsInClass"));
                return t;
            }
        });
        return teachers.get(0);
    }
    
    @Override
    public Teacher findByEmail(String email){
        String sql = "SELECT id, name, surname, email, password, yearOfSchoolClass, nameOfSchoolClass,"
                + "numberOfStudentsInClass FROM BookWareHouse.Teacher WHERE email = " + email;
        List<Teacher> teachers = jdbcTemplate.query(sql, new RowMapper<Teacher>() {
            @Override
            public Teacher mapRow(ResultSet rs, int i) throws SQLException {
                Teacher t = new Teacher();
                t.setId(rs.getLong("id"));
                t.setName(rs.getString("name"));
                t.setSurname(rs.getString("surname"));
                t.setEmail(rs.getString("email"));
                t.setPassword(rs.getString("password"));
                t.setYearOfSchoolClass(rs.getString("yearOfSchoolClass"));
                t.setNameOfSchoolClass(rs.getString("nameOfSchoolClass"));
                t.setNumberOfStudentsInClass(rs.getInt("numberOfStudentsInClass"));
                return t;
            }
        });
        return teachers.get(0);
        }
    
    
    // DELETE
    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM BookWareHouse.teacher WHERE id = " + id;
        try {
            int deleted = jdbcTemplate.update(sql);
            return deleted == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
