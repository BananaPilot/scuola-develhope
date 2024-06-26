package com.teamproject1.scuoledevelhope.classes.user_registry.service;

import com.teamproject1.scuoledevelhope.classes.user_registry.UserRegistry;
import com.teamproject1.scuoledevelhope.classes.user_registry.repo.UserRegistryDAO;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseElement;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseList;
import com.teamproject1.scuoledevelhope.types.errors.SQLException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistryService {

    private final UserRegistryDAO userRegistryDAO;

    public UserRegistryService(UserRegistryDAO userRegistryDAO) {
        this.userRegistryDAO = userRegistryDAO;
    }

    public BaseResponseList<UserRegistry> findAll() {
        return new BaseResponseList<>(userRegistryDAO.findAll());
    }

    public BaseResponseElement<UserRegistry> findById(Long id) {
        Optional<UserRegistry> result = userRegistryDAO.findById(id);
        if (result.isEmpty()) {
            throw new SQLException("UserRegistry was not present");
        }
        return new BaseResponseElement<>(result.get());
    }
}
