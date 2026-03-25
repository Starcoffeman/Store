package org.example.security;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.model.RegisterForm;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/register")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid RegisterForm registerForm, BindingResult result, Model model) {
        if (userRepository.findByUsername(registerForm.getUsername()) != null) {
            model.addAttribute("error", "Пользователь с таким логином уже существует");
            return "register";
        }

        if (result.hasErrors()) {
            log.error("Ошибка регистрации");
            return "register";
        }

        User user = new User();
        user.setUsername(registerForm.getUsername());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        user.setEmail(registerForm.getEmail());
        user.setSurname(registerForm.getSurname());
        user.setName(registerForm.getName());
        user.setAddress(registerForm.getAddress());
        user.setPhone(registerForm.getPhone());
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
        return "redirect:/login";
    }
}