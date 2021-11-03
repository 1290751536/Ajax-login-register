package dao;

import domain.UserDomain;

import java.util.List;

public interface UserDao {
    public List<UserDomain> queryUser(String custNo, String custPwd);

    public boolean isExistUserByCustNo(String custNo);

    public int insertUser(UserDomain userDomain);
}
