package com.workvenue.backend.data.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_VISITOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
