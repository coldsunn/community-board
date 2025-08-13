package com.study.communityboard.controller;

import com.study.communityboard.domain.Category;
import com.study.communityboard.domain.User;
import com.study.communityboard.repository.CategoryRepository;
import com.study.communityboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final CategoryRepository categoryRepository;

    @GetMapping("/home")
    public String home(@RequestParam("userId") Long userId, Model model) {
        User user = userService.getProfile(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
