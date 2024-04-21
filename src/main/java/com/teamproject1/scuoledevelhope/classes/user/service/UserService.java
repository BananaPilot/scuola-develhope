package com.teamproject1.scuoledevelhope.classes.user.service;

import com.teamproject1.scuoledevelhope.classes.user.User;
import com.teamproject1.scuoledevelhope.classes.user.dao.UserDao;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseElement;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseList;
import com.teamproject1.scuoledevelhope.types.errors.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public BaseResponseList<User> getAll() {
        return new BaseResponseList<>(userDao.getAll());
    }

    public BaseResponseElement<User> getByUsername(String username) {
        return new BaseResponseElement<>(userDao.getByUsername(username));
    }

    public BaseResponseElement<User> addUser(User user) {
        int res = userDao.addUser(user.getUsername(), user.getPassword());
        if (res < 1) {
            throw new SQLException("User was not added");
        }
        return new BaseResponseElement<>(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), " ", userDao.getByUsername(user.getUsername()));
    }

    public BaseResponseElement<User> getByID(Long id) {
        return new BaseResponseElement<>(userDao.getByID(id));
    }
}
