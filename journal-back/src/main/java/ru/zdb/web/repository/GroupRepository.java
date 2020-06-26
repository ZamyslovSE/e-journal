package ru.zdb.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.zdb.web.model.Group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Repository
public class GroupRepository {

    @Autowired
    public GroupRepository() {

    }

    public Collection<Group> getGroups() {
        Collection<Group> groups = new ArrayList<>();
        int count = (new Random()).nextInt(8) + 2;
        for (int i = 0; i < count; i++) {
            groups.add(new Group(1, "ММ-15-" + (i +1)));
        }
        return groups;
    }
}
