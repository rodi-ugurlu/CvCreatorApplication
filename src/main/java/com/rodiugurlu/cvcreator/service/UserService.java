package com.rodiugurlu.cvcreator.service;

import com.rodiugurlu.cvcreator.dto.DtoUser;

public interface UserService {
    boolean checkUser(String username, String password);
    DtoUser createUser(DtoUser dtoUser);

}
