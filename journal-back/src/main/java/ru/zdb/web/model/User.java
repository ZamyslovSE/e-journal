package ru.zdb.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "user")
public class User {
    @Id
    String id;
    String username;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
}
