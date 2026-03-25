package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.Product;
import org.example.model.Review;
import org.example.model.User;
import org.example.service.ProductService;
import org.example.service.ReviewService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(Model model) {
        List<Review> reviews = reviewService.findAll();
        model.addAttribute("listReviews", reviews);
        return "review/reviews";
    }

    @GetMapping("/add")
    public String addReviewForm(Model model) {
        model.addAttribute("review", new Review());
        // Получаем список всех пользователей для выпадающего списка
        List<User> users = userService.findAll();
        List<Product> productList = productService.findAll();
        model.addAttribute("listUsers", users);
        model.addAttribute("productList", productList);
        return "review/addReview";
    }

    @GetMapping("/main")
    public String showAddReviewPage(@RequestParam("productID") Integer productID, Model model) {
        Review review = new Review();
        review.setProductID(productID);
        model.addAttribute("review", review);
        // Добавляем список пользователей и для этой формы
        List<User> users = userService.findAll();
        model.addAttribute("listUsers", users);
        return "review/addReviewMain";
    }

    @PostMapping("/main")
    public String addReviewMain(@Valid Review review, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // При ошибках валидации нужно снова добавить список пользователей
            List<User> users = userService.findAll();
            model.addAttribute("listUsers", users);
            return "review/addReviewMain";
        }
        reviewService.save(review);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addReview(@Valid Review review, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // При ошибках валидации нужно снова добавить список пользователей
            List<User> users = userService.findAll();
            model.addAttribute("listUsers", users);
            return "review/addReview";
        }

        reviewService.save(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String editReviewForm(@PathVariable Integer id, Model model) {
        Review review = reviewService.findById(id);
        List<User> users = userService.findAll();
        model.addAttribute("listUsers", users);
        model.addAttribute("review", review);
        return "review/editReview";
    }

    @PostMapping("/edit/{id}")
    public String editReview(@PathVariable Integer id, @Valid Review review, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<User> users = userService.findAll();
            model.addAttribute("listUsers", users);
            return "review/editReview";
        }
        reviewService.update(id, review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Integer id) {
        reviewService.delete(id);
        return "redirect:/reviews";
    }

    @GetMapping("/{userId}")
    public String getReviewsByUserId(@PathVariable Integer userId) {
        reviewService.getByUserId(userId);
        return "redirect:/reviews";
    }
}