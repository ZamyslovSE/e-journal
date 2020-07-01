package ru.zdb.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.zdb.web.model.ApiResponse;
import ru.zdb.web.model.Group;
import ru.zdb.web.repository.GroupRepository;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class GroupController {

    private final GroupRepository repository;

    @Autowired
    public GroupController(GroupRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/groups")
    public ResponseEntity<ApiResponse<Collection<Group>>> getGroups() {
        ApiResponse<Collection<Group>> response = new ApiResponse<>();

        try {
            Collection<Group> groups = repository.findAll();
            response.setContent(groups);
            response.setStatus("0", "OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to retrieve groups", e);
            response.setStatus("500", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.GET, path = "/groups/{userId}")
    public ResponseEntity<ApiResponse<Collection<Group>>> getGroupsByUserId(@PathVariable("userId") String userId) {
        ApiResponse<Collection<Group>> response = new ApiResponse<>();

        try {
            Collection<Group> groups = repository.findAll();
            response.setContent(groups);
            response.setStatus("0", "OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to retrieve groups", e);
            response.setStatus("500", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
