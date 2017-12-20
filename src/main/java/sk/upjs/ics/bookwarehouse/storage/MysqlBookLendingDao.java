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
import sk.upjs.ics.bookwarehouse.BookLending;
import sk.upjs.ics.bookwarehouse.DaoFactory;
import sk.upjs.ics.bookwarehouse.Teacher;

public class MysqlBookLendingDao implements BookLendingDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlBookLendingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //CREATE
    @Override
    public BookLending save(BookLending bookLending) {
        if (bookLending == null) {
            return null;
        }
        if (bookLending.getId() == null) { //INSERT
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("BookLending");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("yearOfReturn", "lended", "returned", "lost",
                    "approved", "comment", "idTeacher", "idBook");
            Map<String, Object> data = new HashMap<>();
            data.put("yearOfReturn", bookLending.getYearOfReturn());
            data.put("lended", bookLending.getLended());
            data.put("returned", bookLending.getReturned());
            data.put("lost", bookLending.getLost());
            if (bookLending.isApproved()) {
                data.put("approved", 1);
            } else {
                data.put("approved", 0);
            }
            data.put("comment", bookLending.getComment());
            data.put("idTeacher", bookLending.getTeacher().getId());
            data.put("idBook", bookLending.getBook().getId());
            bookLending.setId(simpleJdbcInsert.executeAndReturnKey(data).longValue());
        } else {    // UPDATE
            String sql = "UPDATE BookLending SET yearOfReturn = ?,"
                    + " lended = ?, returned = ?, lost = ?,"
                    + " approved = ?, comment = ?  WHERE id = " + bookLending.getId();
            if (bookLending.isApproved()) {
                jdbcTemplate.update(sql, bookLending.getYearOfReturn(), bookLending.getLended(),
                        bookLending.getReturned(), bookLending.getLost(), 1, bookLending.getComment());
            } else {
                jdbcTemplate.update(sql, bookLending.getYearOfReturn(), bookLending.getLended(),
                        bookLending.getReturned(), bookLending.getLost(), 0, bookLending.getComment());
            }
        }
        return bookLending;
    }

    @Override
    public BookLending save(Book book, Teacher teacher, int lended, String comment) {
        BookLending bookLending = new BookLending(book, teacher, lended, comment);
        return save(bookLending);
    }

    @Override
    public BookLending save(Book book, Teacher teacher, int lended, String comment, int yearOfReturn) {
        BookLending bookLending = new BookLending(book, teacher, lended, comment, yearOfReturn);
        return save(bookLending);
    }

    //READ
    @Override
    public List<BookLending> getAll() {
        String sql = "SELECT id, yearOfReturn, lended, returned, lost, approved,"
                + " comment, idTeacher, idBook FROM BookWareHouse.BookLending;";
        List<BookLending> bookLendings = jdbcTemplate.query(sql, new RowMapper<BookLending>() {
            @Override
            public BookLending mapRow(ResultSet rs, int i) throws SQLException {
                BookLending bl = new BookLending();
                bl.setId(rs.getLong("id"));
                bl.setYearOfReturn(rs.getInt("yearOfReturn"));
                bl.setLended(rs.getInt("lended"));
                bl.setReturned(rs.getInt("returned"));
                bl.setLost(rs.getInt("lost"));
                bl.setComment(rs.getString("comment"));

                //INITIALIZATION OF TEACHER
                TeacherDao teacherDao = DaoFactory.INSTANCE.getTeacherDao();
                long idTeacher = rs.getLong("idTeacher");
                Teacher t = teacherDao.findById(idTeacher);
                bl.setTeacher(t);

                //INITIALIZATION OF BOOK
                BookDao bd = DaoFactory.INSTANCE.getBookDao();
                long idBook = rs.getLong("idBook");
                Book b = bd.findById(idBook);
                bl.setBook(b);

                //INITIALIZATION OF APPROVED
                if (rs.getInt("approved") == 1) {
                    bl.setApproved(true);
                } else {
                    bl.setApproved(false);
                }

                return bl;
            }

        });
        return bookLendings;
    }

    // DELETE
    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM BookWareHouse.BookLending WHERE id = " + id;
        try {
            int deleted = jdbcTemplate.update(sql);
            return deleted == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public BookLending findById(long id) {
        List<BookLending> list = getAll();
        for (BookLending bookLending : list) {
            if (bookLending.getId() == id) {
                return bookLending;
            }
        }
        return null;
    }
}
