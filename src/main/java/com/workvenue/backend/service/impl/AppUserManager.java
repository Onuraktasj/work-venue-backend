package com.workvenue.backend.service.impl;

import com.workvenue.backend.data.model.AppUser;
import com.workvenue.backend.data.model.AppUserRole;
import com.workvenue.backend.service.AppUserService;

import java.util.List;

public class AppUserManager implements AppUserService {
    @Override
    public AppUser saveAppUser(AppUser appUser) {
        return null;
    }

    @Override
    public AppUserRole saveAppUserRole(AppUserRole appUserRole) {
        return null;
    }

    @Override
    public void addAppUserRoleToAppUser(String username, String appUserRole) {

    }

    @Override
    public AppUser getAppUser(String userName) {
        return null;
    }

    @Override
    public List<AppUser> getAppUsers() {
        return null;
    }
}
