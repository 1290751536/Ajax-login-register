package dao.impl;

import dao.UserDao;
import domain.UserDomain;
import org.junit.Test;

import static org.junit.Assert.*;

public class implUserDaoTest {

    @Test
    public void queryUser() {
        UserDao userDao = new implUserDao();
        System.out.println(userDao.queryUser("123", "123"));
    }

    @Test
    public void isExistUserBycustNo() {
        UserDao userDao = new implUserDao();
        System.out.println(userDao.isExistUserByCustNo("1243"));
    }

    @Test
    public void insertUser() {
        UserDao userDao = new implUserDao();
        UserDomain userDomain = new UserDomain();
        userDomain.setCustNo("77");
        userDomain.setCustPwd("77");
        userDomain.setEmail("77@163.com");
        System.out.println(userDao.insertUser(userDomain));
    }

}