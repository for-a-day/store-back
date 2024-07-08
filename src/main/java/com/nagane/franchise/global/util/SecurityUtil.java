package com.nagane.franchise.global.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nagane.franchise.global.config.CustomUserDetails;
import com.nagane.franchise.store.dao.AdminRepository;
import com.nagane.franchise.store.domain.Admin;

@Component
public class SecurityUtil {


    public static CustomUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }
        return null;
    }

    

}