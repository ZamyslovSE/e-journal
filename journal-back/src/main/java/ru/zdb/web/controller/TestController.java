package ru.zdb.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.zdb.web.model.Group;
import ru.zdb.web.repository.GroupRepository;

import java.util.Collection;

@Slf4j
@RestController("/api/v1")
public class TestController {

    private GroupRepository repository;

    @Autowired
    public TestController(GroupRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/groups")
    public ResponseEntity<Collection<Group>> getGroups() {
        Collection<Group> groups = null;
        try {
            groups = repository.getGroups();
//            log.info("Retrieved groups: " + groups);
            return new ResponseEntity<Collection<Group>>(groups, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to retrieve groups", e);
            return new ResponseEntity<Collection<Group>>(groups, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.GET, path = "/groups/{userId}")
    public ResponseEntity<Collection<Group>> getGroupsByUserId(@PathVariable("userId") String userId) {
        Collection<Group> groups = null;
        try {
            groups = repository.getGroups();
            log.info("Retrieved groups: " + groups);
            return new ResponseEntity<Collection<Group>>(groups, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to retrieve groups", e);
            return new ResponseEntity<Collection<Group>>(groups, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
