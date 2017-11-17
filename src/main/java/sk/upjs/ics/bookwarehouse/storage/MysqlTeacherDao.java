package sk.upjs.ics.bookwarehouse.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.ics.bookwarehouse.Teacher;

public class MysqlTeacherDao implements TeacherDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlTeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //CREATE
    @Override
    public void save(Teacher teacher) {
        if (teacher == null) {
            return;
        }
        if (teacher.getId() == null) { //INSERT
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("Teacher");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("name", "surname", "email", "password",
                    "yearOfSchoolClass", "nameOfSchoolClass", "numberOfStudentsInClass");
            Map<String, Object> data = new HashMap<>();
            data.put("name", teacher.getName());
            data.put("surname", teacher.getSurname());
            data.put("email", teacher.getEmail());
            data.put("password", teacher.getPassword());
            data.put("yearOfSchoolClass", teacher.getYearOfSchoolClass());
            data.put("nameOfSchoolClass", teacher.getNameOfSchoolClass());
            data.put("numberOfStudentsInClass", teacher.getNumberOfStudentsInClass());
            teacher.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
        } else {    // UPDATE
            String sql = "UPDATE admin SET name = ?, SET surname = ?, SET email = ?, "
                    + "SET password = ?, SET yearOfSchoolClass = ?, SET nameOfSchoolClass = ? "
                    + "WHERE id = " + teacher.getId();
            jdbcTemplate.update(sql, teacher.getName(), teacher.getSurname(), teacher.getEmail(),
                    teacher.getPassword(), teacher.getYearOfSchoolClass(), teacher.getNameOfSchoolClass());
        }
    }

    //READ
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
    public Teacher findByEmail(String email) {
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
