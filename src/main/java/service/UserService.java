package service;

import domain.UserDomain;

import java.util.List;

public interface UserService {
    public List<UserDomain> queryUser(String custNo, String custPwd);

    public boolean isExitUserByCustNo(String custNo);

    public int insertUser(UserDomain userDomain);
}
