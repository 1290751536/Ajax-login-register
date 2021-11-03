package dao.impl;

import dao.UserDao;
import domain.UserDomain;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class implUserDao implements UserDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<UserDomain> queryUser(String custNo, String custPwd) {
        String sql = "SELECT * from customer_table\n" +
                "WHERE cust_no=? AND cust_pwd=?";
        List<UserDomain> user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserDomain>(UserDomain.class), custNo, custPwd);
        return user;
    }

    @Override
    public boolean isExistUserByCustNo(String custNo) {
        String sql = "SELECT COUNT(*) from customer_table\n" +
                "WHERE cust_no=?";
        int cnt = jdbcTemplate.queryForObject(sql, Integer.class, custNo);
        return cnt >= 1;
    }

    @Override
    public int insertUser(UserDomain userDomain) {
        String sql = "INSERT INTO customer_table(cust_no,cust_pwd,email)\n" +
                "VALUES(?,?,?)";
        int cnt = 0;
        try {
            cnt = jdbcTemplate.update(sql, userDomain.getCustNo(), userDomain.getCustPwd(), userDomain.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return cnt;
    }
}
