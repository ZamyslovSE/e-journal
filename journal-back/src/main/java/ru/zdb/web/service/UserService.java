package ru.zdb.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zdb.web.model.User;
import ru.zdb.web.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
