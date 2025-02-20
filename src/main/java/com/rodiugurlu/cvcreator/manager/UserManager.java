package com.rodiugurlu.cvcreator.manager;

import com.rodiugurlu.cvcreator.dto.DtoUser;
import com.rodiugurlu.cvcreator.entity.User;
import com.rodiugurlu.cvcreator.repository.UserRepository;
import com.rodiugurlu.cvcreator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public DtoUser createUser(DtoUser dtoUser) {
        // Parolayı şifrele
        String hashedPassword = passwordEncoder.encode(dtoUser.getPassword());

        // Kullanıcıyı entity'ye dönüştür ve kaydet
        User user = new User();
        user.setUsername(dtoUser.getUsername());
        user.setPassword(hashedPassword);
        userRepository.save(user);

        return dtoUser;
    }

    @Override
    public boolean checkUser(String username, String password) {
        Optional<User> optional = userRepository.findByUsernameAndPassword(username, password);
        if (optional.isPresent()) {
            User user = optional.get();
            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(user, dtoUser);
            return true;
        }
        return false;
    }

    // Kullanıcı adının zaten alınıp alınmadığını kontrol et
    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public ResponseEntity<String> updatePassword(String currentPassword, String newPassword) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isPresent()) {
            User user = optional.get();
            if (passwordEncoder.matches(currentPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
            } else {
                ResponseEntity.badRequest().body("Mevcut şifre yanlış.");

            }

        }
        return ResponseEntity.badRequest().body("BIR HATA OLUSTU");
    }
}
