package com.nagane.franchise.global.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

    private final String id;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
    		String adminId) {
        super(username, password, authorities);
        this.id = adminId;
    }

    public String getId() {
        return id;
    }
}
