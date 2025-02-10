package com.rodiugurlu.cvcreator.controller;

import com.rodiugurlu.cvcreator.entity.User;
import com.rodiugurlu.cvcreator.repository.UserRepository;
import com.rodiugurlu.cvcreator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // Login sayfası
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        Optional<User> optional = userRepository.findByUsernameAndPassword(username, passwordEncoder.encode(password));
        if (optional.isPresent()) {

            return "redirect:/dashboard";
        } else {
            return "redirect:/login?error=true";

        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password) {

        com.rodiugurlu.cvcreator.entity.User newUser = new com.rodiugurlu.cvcreator.entity.User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);

        return "redirect:/login?success";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("username", username);
        return "dashboard";
    }
}
