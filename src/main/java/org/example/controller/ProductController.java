package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.Category;
import org.example.model.Product;
import org.example.model.Review;
import org.example.model.User;
import org.example.service.CategoryService;
import org.example.service.ProductService;
import org.example.service.ReviewService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        List<Product> products = productService.findAll();
        products.forEach(product -> {
            List<Review> reviews = reviewService.findByProductID(product.getId());
            product.setReviews(reviews);
        });
        model.addAttribute("listProducts", products);
        return "product/products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("categories",categories);
        return "product/addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "product/addProduct";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/editProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, @Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "product/editProduct";
        }
        productService.update(id, product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/products";
    }
}