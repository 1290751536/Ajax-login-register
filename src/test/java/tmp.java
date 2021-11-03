import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class tmp {
    @Test
    public void tmp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select count(*) from book_table";
        int cnt = 0;
        cnt = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(cnt);
    }
}
