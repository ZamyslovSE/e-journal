package ru.zdb.web.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class Group {
    long id;
    String name;
    Collection<Student> members;

    public Group(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group() {
    }
}
