package ru.zdb.web.model;

import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

@Getter
public class UserDetailsImpl extends org.springframework.security.core.userdetails.User { //TODO authorization (roles)

    private final User user;

    public UserDetailsImpl(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }
}