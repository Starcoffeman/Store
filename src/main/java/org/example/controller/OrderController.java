package org.example.controller;


import org.example.model.Order;
import org.example.model.Product;
import org.example.model.User;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @GetMapping
    public String index(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("listOrders", orders);
        return "order/orders";
    }

    @GetMapping("profile/{id}")
    public String viewDetailOrder(Model model, @PathVariable Integer id) {
        Order order = orderService.findById(id);
        User user = userService.findById(orderService.findById(id).getUserID());
        List<Product> items = orderService.findAllProductById(id);
        model.addAttribute("order",order);
        model.addAttribute("items",items);
        model.addAttribute("user",user);
        return "order/viewOrder";
    }

    @GetMapping("/checkout")
    public String showCheckoutPage(@RequestParam Long userID,
                                   @RequestParam Double totalPrice,
                                   Model model) {
        model.addAttribute("userID", userID);
        model.addAttribute("totalPrice", totalPrice);
        return "checkout";
    }

    @PostMapping("/place")
    public String placeOrder(@RequestParam Integer userID,
                             @RequestParam Double totalPrice) {
        Order order = new Order();
        order.setUserID(userID);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("В обработке");
        order.setTotalPrice(totalPrice);
        orderService.save(order);
        return "redirect:/order-success";
    }

    @GetMapping("/add")
    public String addOrderForm(Model model) {
        List<User> listUsers = userService.findAll();
        List<Product> list = productService.findAll();
        model.addAttribute("listUsers",listUsers);
        model.addAttribute("listProducts",list);
        model.addAttribute("orderDto", new Order());
        return "order/add";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order orderDto) {
        orderDto.setOrderDate(LocalDateTime.now());
        orderService.save(orderDto);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable Integer id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("orderDto", order);
        return "order/edit";
    }

    @PostMapping("/edit/{id}")
    public String editOrder(@PathVariable Integer id, @ModelAttribute Order orderDto) {
//        Order order = orderService.findById(id);
//        order.setUserID(orderDto.getUserID());
//        order.setStatus(orderDto.getStatus());
//        order.setTotalPrice(orderDto.getTotalPrice());
        orderService.save(orderDto);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        orderService.delete(id);
        return "redirect:/orders";
    }
}