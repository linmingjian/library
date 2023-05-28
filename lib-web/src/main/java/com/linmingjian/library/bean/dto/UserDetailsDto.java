package com.linmingjian.library.bean.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UserDetailsDto extends User {
    private com.linmingjian.library.bean.entity.User user;

    public UserDetailsDto(com.linmingjian.library.bean.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }
}
