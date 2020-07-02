package ru.zdb.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zdb.web.model.Group;
import ru.zdb.web.model.User;

import java.util.Collection;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
//    Collection<User> findAll();
    User findUserByUsername(String username);
//    User save(User user);
}