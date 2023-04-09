package com.workvenue.backend.service;

import com.workvenue.backend.data.model.AppUser;
import com.workvenue.backend.data.model.AppUserRole;

import java.util.List;

public interface AppUserService {

    AppUser saveAppUser(AppUser appUser);

    AppUserRole saveAppUserRole(AppUserRole appUserRole);

    void addAppUserRoleToAppUser(String username, String appUserRole);

    AppUser getAppUser(String userName);

    List<AppUser> getAppUsers();
}
