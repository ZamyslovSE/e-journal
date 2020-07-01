package ru.zdb.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.zdb.web.model.Group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    Collection<Group> findAll();
}
