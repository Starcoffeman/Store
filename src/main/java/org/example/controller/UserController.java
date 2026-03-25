package org.example.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Order;
import org.example.model.Review;
import org.example.model.User;
import org.example.service.OrderService;
import org.example.service.ReviewService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("listUsers", users);
        return "user/users";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = userService.getCurrentUser();
        log.error(user.getName());
        List<Review> reviews = reviewService.getByUserId(user.getId());
        int ordersCount = orderService.getByUserId(user.getId()).size();
        List<Order> recentOrders = orderService.getByUserId(user.getId());
        model.addAttribute("recentOrders",recentOrders);
        model.addAttribute("ordersCount",ordersCount);
        model.addAttribute("registrationDate",user.getRegistrationDate());
        model.addAttribute("reviewsCount",reviews.size());
        model.addAttribute("recentReviews", reviews);
        model.addAttribute("user",user);
        return "user/profile";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/addUser";
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/addUser";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(LocalDate.now());
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/editUser";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable int id, @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/editUser";
        }
        userService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("profile/edit/{id}")
    public String editUserInProfile(@PathVariable int id, @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/editUser";
        }
        userService.updateInProfile(id, user);
        return "redirect:/users/profile";
    }
}
