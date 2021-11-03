package service.impl;

import dao.UserDao;
import dao.impl.implUserDao;
import domain.UserDomain;
import service.UserService;

import java.util.List;

public class implUserService implements UserService {
    private UserDao userDao = new implUserDao();

    @Override
    public List<UserDomain> queryUser(String custNo, String custPwd) {
        return userDao.queryUser(custNo, custPwd);
    }

    @Override
    public boolean isExitUserByCustNo(String custNo) {
        return userDao.isExistUserByCustNo(custNo);
    }

    @Override
    public int insertUser(UserDomain userDomain) {
        return userDao.insertUser(userDomain);
    }
}
