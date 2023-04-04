package com.ewok.springbootproject.config.auth.dto;

import com.ewok.springbootproject.domain.user.Member;
import com.ewok.springbootproject.domain.user.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;
    private String sub;
    private Role role;

    public SessionUser(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.sub = member.getSub();
        this.role = member.getRole();
    }
}
