package sk.upjs.ics.bookwarehouse.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.ics.bookwarehouse.Book;

/**
 *
 * @author tomas
 */
public class MysqlBookDao implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book save(Book book) {
        if (book == null) {
            return null;
        }
        if (book.getId() == null) { //INSERT
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("Book");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("title", "author", "yearOfPublication", "schoolClass", "numberInStock",
                    "numberOfUsed", "isUsed", "comment");
            Map<String, Object> data = new HashMap<>();
            data.put("title", book.getTitle());
            data.put("author", book.getAuthor());
            data.put("yearOfPublication", book.getYearOfPublication());
            data.put("schoolClass", book.getSchoolClass());
            data.put("numberInStock", book.getNumberInStock());
            data.put("numberOfUsed", book.getNumberOfUsed());
            if (book.isUsed()) {
                data.put("isUsed", 1);
            } else {
                data.put("isUsed", 1);
            }
            data.put("comment", book.getComment());
            book.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
        } else {
            // UPDATE
            String sql = "UPDATE book SET title = ?, author = ?, yearOfPublication = ?,"
                    + " schoolClass = ?, numberInStock = ?, numberOfUsed = ?,"
                    + "isUsed = ?, comment = ? WHERE id =" + book.getId();
            if (book.isUsed()) {
                jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(),
                        book.getYearOfPublication(), book.getSchoolClass(),
                        book.getNumberInStock(), book.getNumberOfUsed(),
                        1, book.getComment());
            } else {
                jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(),
                        book.getYearOfPublication(), book.getSchoolClass(),
                        book.getNumberInStock(), book.getNumberOfUsed(),
                        0, book.getComment());
            }
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT id, title, author, yearOfPublication, schoolClass, numberInStock,"
                + "numberOfUsed, isUsed, comment FROM BookWareHouse.Book;";
        List<Book> books = jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int i) throws SQLException {
                Book b = new Book();
                b.setId(rs.getLong("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setYearOfPublication(rs.getInt("yearOfPublication"));
                b.setSchoolClass(rs.getString("schoolClass"));
                b.setNumberInStock(rs.getInt("numberInStock"));
                b.setNumberOfUsed(rs.getInt("numberOfUsed"));
                b.setComment(rs.getString("comment"));
                int isUsedNumber = rs.getInt("isUsed");
                if (isUsedNumber == 1) {
                    b.setUsed(true);
                } else {
                    b.setUsed(false);
                }
                
                return b;
            }
        });
        return books;
    }

   /* @Override
    public Book findById(long id) {
        String sql = "SELECT id, title, author, yearOfPublication, schoolClass, numberInStock,"
                + "numberOfUsed, isUsed, comment FROM BookWareHouse.Book WHERE id = " + id;
        List<Book> books = jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int i) throws SQLException {
                Book b = new Book();
                b.setId(id);
                b.setAuthor(rs.getString("author"));
                b.setYearOfPublication(rs.getInt("yearOfPublication"));
                b.setSchoolClass(rs.getString("schoolClass"));
                b.setNumberInStock(rs.getInt("numberInStock"));
                b.setNumberOfUsed(rs.getInt("numberOfUsed"));
                b.setComment(rs.getString("comment"));
                int isUsedNumber = rs.getInt("isUsed");
                if (isUsedNumber == 1) {
                    b.setUsed(true);
                } else {
                    b.setUsed(false);
                }

                return b;
            }
        });
        return null;
    }*/
    
    @Override
    public Book findById(long id) {
        List<Book> list = getAll();
        for (Book book : list) {
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    // DELETE
    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM BookWareHouse.Book WHERE id = " + id;
        try {
            int deleted = jdbcTemplate.update(sql);
            return deleted == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
