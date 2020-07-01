package ru.zdb.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "academic_group")
public class Group {

    @Id
    long id;
    String name;
//    Collection<Student> members;

    public Group(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group() {
    }
}
