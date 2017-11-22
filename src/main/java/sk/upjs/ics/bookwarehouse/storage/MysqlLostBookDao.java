package sk.upjs.ics.bookwarehouse.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.ics.bookwarehouse.Admin;
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.LostBook;

public class MysqlLostBookDao implements LostBookDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlLostBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    @Override
    public LostBook save(BookLending bookLending, Admin admin, String comment) {
        if (bookLending == null || admin == null) {
            return null;
        }
        LostBook lostBook = new LostBook(bookLending, admin, comment);
        return save(lostBook);
    }

    @Override
    public LostBook save(LostBook lostBook) {
        if (lostBook == null) {
            return null;
        }
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("lostBook");
        simpleJdbcInsert.usingGeneratedKeyColumns("id");
        simpleJdbcInsert.usingColumns("idBook", "title", "author", "yearOfPublication", "schoolClass", "number",
                "idTeacher", "nameOfTeacher", "surnameOfTeacher", "idAdmin", "date", "comment", "usernameOfAdmin");
        Map<String, Object> data = new HashMap<>();
        data.put("idBook", lostBook.getIdBook());
        data.put("title", lostBook.getTitle());
        data.put("author", lostBook.getAuthor());
        data.put("yearOfPublication", lostBook.getYearOfPublication());
        data.put("schoolClass", lostBook.getSchoolClass());
        data.put("number", lostBook.getNumber());
        data.put("idTeacher", lostBook.getIdTeacher());
        data.put("nameOfTeacher", lostBook.getNameOfTeacher());
        data.put("surnameOfTeacher", lostBook.getSurnameOfTeacher());
        data.put("idAdmin", lostBook.getIdAdmin());
        data.put("date", lostBook.getDate());
        data.put("comment", lostBook.getComment());
        data.put("usernameOfAdmin", lostBook.getUsernameOfAdmin());
        lostBook.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
        return lostBook;
    }

    // READ
    @Override
    public List<LostBook> getAll() {
        String sql = "SELECT id, idBook, title, author, yearOfPublication, schoolClass, number, "
                + "idTeacher,nameOfTeacher, surnameOfTeacher, idAdmin, date, comment, "
                + "usernameOfAdmin FROM BookWareHouse.LostBook;";
        List<LostBook> lostBooks = jdbcTemplate.query(sql, new RowMapper<LostBook>() {
            @Override
            public LostBook mapRow(ResultSet rs, int i) throws SQLException {
                LostBook lostBook = new LostBook();
                lostBook.setId(rs.getLong("id"));
                lostBook.setIdBook(rs.getLong("idBook"));
                lostBook.setTitle(rs.getString("title"));
                lostBook.setAuthor(rs.getString("author"));
                lostBook.setYearOfPublication(rs.getInt("yearOfPublication"));
                lostBook.setSchoolClass(rs.getString("schoolClass"));
                lostBook.setNumber(rs.getInt("number"));
                lostBook.setIdTeacher(rs.getLong("idTeacher"));
                lostBook.setNameOfTeacher(rs.getString("nameOfTeacher"));
                lostBook.setSurnameOfTeacher(rs.getString("surnameOfTeacher"));
                lostBook.setIdAdmin(rs.getLong("idAdmin"));
                lostBook.setDate(rs.getTimestamp("date").toLocalDateTime());
                lostBook.setComment(rs.getString("comment"));
                lostBook.setUsernameOfAdmin(rs.getString("userNameOfAdmin"));
                return lostBook;
            }
        });
        Collections.sort(lostBooks, new Comparator<LostBook>() {
            @Override
            public int compare(LostBook lb1, LostBook lb2) {
                return lb1.getDate().compareTo(lb2.getDate());
            }
        });
        return lostBooks;
    }

    @Override
    public List<LostBook> getByYear(int year) {
        String sql = "SELECT id, idBook, title, author, yearOfPublication, schoolClass, number, "
                + "idTeacher,nameOfTeacher, surnameOfTeacher, idAdmin, date, comment, "
                + "usernameOfAdmin FROM BookWareHouse.LostBook WHERE YEAR(date) = " + year;
        List<LostBook> lostBooks = jdbcTemplate.query(sql, new RowMapper<LostBook>() {
            @Override
            public LostBook mapRow(ResultSet rs, int i) throws SQLException {
                LostBook ldb = new LostBook();
                ldb.setId(rs.getLong("id"));
                ldb.setIdBook(rs.getLong("idBook"));
                ldb.setTitle(rs.getString("title"));
                ldb.setAuthor(rs.getString("author"));
                ldb.setYearOfPublication(rs.getInt("yearOfPublication"));
                ldb.setSchoolClass(rs.getString("schoolClass"));
                ldb.setNumber(rs.getInt("number"));
                ldb.setIdTeacher(rs.getLong("idTeacher"));
                ldb.setNameOfTeacher(rs.getString("nameOfTeacher"));
                ldb.setSurnameOfTeacher(rs.getString("surnameOfTeacher"));
                ldb.setIdAdmin(rs.getLong("idAdmin"));
                ldb.setDate(rs.getTimestamp("date").toLocalDateTime());
                ldb.setComment(rs.getString("comment"));
                ldb.setUsernameOfAdmin(rs.getString("userNameOfAdmin"));
                return ldb;
            }
        });
        Collections.sort(lostBooks, new Comparator<LostBook>() {
            @Override
            public int compare(LostBook lb1, LostBook lb2) {
                return lb1.getDate().compareTo(lb2.getDate());
            }
        });
        return lostBooks;
    }

    // DELETE
    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM BookWareHouse.lostBook WHERE id = " + id;
        try {
            int deleted = jdbcTemplate.update(sql);
            return deleted == 1;
        } catch (Exception e) {
            return false;
        }
    }
}



 /* @Override
    public void save(BookLending bookLending, Admin admin, String comment) {
        if (bookLending == null || admin == null) {
            return;
        }
        LostBook lb = new LostBook();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("lostBook");
        simpleJdbcInsert.usingGeneratedKeyColumns("id");
        simpleJdbcInsert.usingColumns("idBook", "title", "author", "yearOfPublication", "schoolClass", "number",
                "idTeacher", "nameOfTeacher", "surnameOfTeacher", "idAdmin", "date", "comment", "usernameOfAdmin");
        Map<String, Object> data = new HashMap<>();
        data.put("idBook", bookLending.getBook().getId());
        data.put("title", bookLending.getBook().getTitle());
        data.put("author", bookLending.getBook().getAuthor());
        data.put("yearOfPublication", bookLending.getBook().getYearOfPublication());
        data.put("schoolClass", bookLending.getBook().getSchoolClass());
        data.put("number", bookLending.getLost());
        data.put("idTeacher", bookLending.getTeacher().getId());
        data.put("nameOfTeacher", bookLending.getTeacher().getName());
        data.put("surnameOfTeacher", bookLending.getTeacher().getSurname());
        data.put("idAdmin", admin.getId());
        data.put("date", LocalDateTime.now());
        data.put("comment", comment);
        data.put("usernameOfAdmin", admin.getUserName());
        lb.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
    }*/
