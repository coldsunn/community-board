package com.study.communityboard.controller;

import com.study.communityboard.domain.Department;
import com.study.communityboard.domain.Role;
import com.study.communityboard.domain.User;
import com.study.communityboard.dto.JoinDto;
import com.study.communityboard.dto.LoginDto;
import com.study.communityboard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/auth/login")
    public String loginForm() {
        return "auth/login";
    }

    @PostMapping("/auth/login")
    public String login(LoginDto loginDto, RedirectAttributes redirectAttributes) {
        User user = userService.login(loginDto.getStudentId(), loginDto.getPassword());

        if (user != null) {
            redirectAttributes.addAttribute("userId", user.getId());
            redirectAttributes.addAttribute("department", user.getDepartment());
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("error", "학번 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/auth/join")
    public String joinForm(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        model.addAttribute("departments", Department.values());
        return "auth/join";
    }

    @PostMapping("/auth/join")
    public String join(@ModelAttribute("joinDto") @Valid JoinDto joinDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "입력된 정보가 유효하지 않습니다.");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.joinDto", bindingResult);
            return "redirect:/auth/join";
        }

        try {
            userService.register(
                    joinDto.getStudentId(),
                    joinDto.getNickname(),
                    joinDto.getPassword(),
                    joinDto.getDepartment(),
                    joinDto.getRole()
            );

            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다. 로그인 해주세요.");
            return "redirect:/auth/login";
        } catch (Exception e) {
            log.error("회원가입 실패: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/join";
        }
    }

    @GetMapping("/auth/withdraw")
    public String withdrawForm(@RequestParam("userId") Long userId, Model model) {
        if (userService.getProfile(userId) == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("userId", userId);
        return "auth/withdraw";
    }

    @PostMapping("/auth/withdraw")
    public String withdraw(@RequestParam("userId") Long userId,
                           @RequestParam("confirmText") String confirmText,
                           RedirectAttributes redirectAttributes) {
        if (!"탈퇴합니다".equals(confirmText)) {
            redirectAttributes.addFlashAttribute("error", "확인 문구가 올바르지 않습니다.");
            return "redirect:/auth/withdraw?userId=" + userId;
        }

        userService.withdraw(userId);
        redirectAttributes.addFlashAttribute("message", "회원 탈퇴가 완료되었습니다.");
        return "redirect:/";
    }

}


