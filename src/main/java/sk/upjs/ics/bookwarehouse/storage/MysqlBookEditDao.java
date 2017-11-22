package sk.upjs.ics.bookwarehouse.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.ics.bookwarehouse.Book;
import sk.upjs.ics.bookwarehouse.BookEdit;
import sk.upjs.ics.bookwarehouse.DaoFactory;

public class MysqlBookEditDao implements BookEditDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlBookEditDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    @Override
    public BookEdit save(BookEdit bookEdit) {
        if (bookEdit == null) {
            return null;
        }
        if (bookEdit.getId() == null) { //INSERT
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("BookEdit");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("idBook", "nameOfAdmin", "date",
                    "numberBefore", "numberAfter", "comment");
            Map<String, Object> data = new HashMap<>();
            data.put("idBook", bookEdit.getBook().getId());
            data.put("nameOfAdmin", bookEdit.getNameOfAdmin());
            data.put("date", bookEdit.getDate());
            data.put("numberBefore", bookEdit.getNumberBefore());
            data.put("numberAfter", bookEdit.getNumberAfter());
            data.put("comment", bookEdit.getComment());
            bookEdit.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
        } else {    // UPDATE
            String sql = "UPDATE BookEdit idBook = ?, nameOfAdmin = ?, "
                    + "date = ?, numberBefore = ?, numberAfter = ?, "
                    + "comment = ?,  WHERE id = " + bookEdit.getId();
            jdbcTemplate.update(sql, bookEdit.getBook().getId(), bookEdit.getNameOfAdmin(),
                    bookEdit.getDate(), bookEdit.getNumberBefore(),
                    bookEdit.getNumberAfter(), bookEdit.getComment());
        }
        return bookEdit;
    }

    // READ
    @Override
    public List<BookEdit> getAll() {
        String sql = "SELECT id, idBook, nameOfAdmin, date, numberBefore,"
                + "numberAfter, comment FROM BookWareHouse.BookEdit;";
        List<BookEdit> bookEdits = jdbcTemplate.query(sql, new RowMapper<BookEdit>() {
            @Override
            public BookEdit mapRow(ResultSet rs, int i) throws SQLException {
                BookEdit bookEdit = new BookEdit();
                bookEdit.setId(rs.getLong("id"));
                bookEdit.setNameOfAdmin(rs.getString("nameOfAdmin"));
                bookEdit.setDate(rs.getTimestamp("id").toLocalDateTime());
                bookEdit.setNumberBefore(rs.getInt("numberBefore"));
                bookEdit.setNumberBefore(rs.getInt("numberAfter"));
                bookEdit.setComment(rs.getString("comment"));

                //INITIALIZATION OF BOOK
                long idBook = rs.getLong("idBook");
                BookDao bookDao = DaoFactory.INSTANCE.getBookDao();
                Book book = bookDao.findById(idBook);
                bookEdit.setBook(book);

                return bookEdit;
            }
        });
        return bookEdits;
    }

    // UPDATE
    // DONT WANT TO UPDATE
    // DELETE
    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM BookWareHouse.BookEdit WHERE id = " + id;
        try {
            int deleted = jdbcTemplate.update(sql);
            return deleted == 1;
        } catch (Exception e) {
            return false;
        }
    }

}
