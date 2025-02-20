package com.rodiugurlu.cvcreator.service;

import com.rodiugurlu.cvcreator.dto.DtoUser;
import org.springframework.http.ResponseEntity;

public interface UserService {
    boolean checkUser(String username, String password);
    DtoUser createUser(DtoUser dtoUser);
    ResponseEntity updatePassword(String currentPassword, String newPassword);

}
