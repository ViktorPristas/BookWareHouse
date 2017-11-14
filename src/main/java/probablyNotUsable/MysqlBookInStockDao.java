package probablyNotUsable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import probablyNotUsable.BookInStock;

public class MysqlBookInStockDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public MysqlBookInStockDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<BookInStock> getAll() {
        String sql = "SELECT id, idBook, idStock, number FROM BookWareHouse.BookInStock;";
        List<BookInStock> bookInStocks = jdbcTemplate.query(sql, new RowMapper<BookInStock>() {
            @Override
            public BookInStock mapRow(ResultSet rs, int i) throws SQLException {
                BookInStock bis = new BookInStock();
                bis.setId(rs.getLong("id"));
                bis.setIdBook(rs.getLong("idBook"));
                bis.setIdStock(rs.getLong("idStock"));
                bis.setNumber(rs.getInt("number"));
                return bis;
            }
        });
        return bookInStocks;
    }
}
