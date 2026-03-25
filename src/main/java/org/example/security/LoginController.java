package org.example.security;

import org.example.model.LoginForm;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    ProductService productService;

    @GetMapping
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "redirect:/products";
    }
}

