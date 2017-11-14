package probablyNotUsable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import probablyNotUsable.Stock;

public class MysqlStockDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlStockDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Stock> getAll() {
        String sql = "SELECT id, name, comment FROM BookWareHouse.Stock;";
        List<Stock> stocks = jdbcTemplate.query(sql, new RowMapper<Stock>() {
            @Override
            public Stock mapRow(ResultSet rs, int i) throws SQLException {
                Stock s = new Stock();
                s.setId(rs.getLong("id"));
                s.setName(rs.getString("name"));
                s.setCommnet(rs.getString("comment"));
                return s;
            }
        });
        return stocks;
    }
}
