package com.study.communityboard.controller;

import com.study.communityboard.domain.Board;
import com.study.communityboard.domain.Category;
import com.study.communityboard.domain.Comment;
import com.study.communityboard.domain.User;
import com.study.communityboard.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    private final BoardService boardService;
    private final CategoryService categoryService;
    private final BoardLikeService boardLikeService;
    private final BoardScrapService boardScrapService;
    private final CommentService commentService;

    // 마이페이지로 이동
    @GetMapping("/mypage")
    public String mypageMain(@RequestParam("userId") Long userId, Model model) {
        User user = userService.getProfile(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        List<Category> categories = categoryService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        return "mypage/index";
    }

    // 내가 쓴 글로 이동
    @GetMapping("/mypage/posts")
    public String myPosts(@RequestParam("userId") Long userId, Model model) {
        User user = userService.getProfile(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        List<Board> userBoards = boardService.listByUser(userId);
        List<Category> categories = categoryService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("userBoards", userBoards);
        model.addAttribute("categories", categories);
        return "mypage/posts";
    }

    // 내가 쓴 댓글로 이동
    @GetMapping("/mypage/comments")
    public String myComments(@RequestParam("userId") Long userId, Model model) {
        User user = userService.getProfile(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        List<Comment> comments = commentService.listByUser(userId);
        List<Category> categories = categoryService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("comments", comments);
        model.addAttribute("categories", categories);
        return "mypage/comments";
    }

    // 내가 좋아요한 게시글 목록으로 이동
    @GetMapping("/mypage/likes")
    public String myLikes(@RequestParam("userId") Long userId, Model model) {
        User user = userService.getProfile(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        List<Board> likedBoards = boardLikeService.listLikedBoards(userId);
        List<Category> categories = categoryService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("likedBoards", likedBoards);
        model.addAttribute("categories", categories);
        return "mypage/likes";
    }

    // 내가 스크랩한 게시글 목록으로 이동
    @GetMapping("/mypage/scraps")
    public String myScraps(@RequestParam("userId") Long userId, Model model) {
        User user = userService.getProfile(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        List<Board> scrappedBoards = boardScrapService.listScrappedBoards(userId);
        List<Category> categories = categoryService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("scrappedBoards", scrappedBoards);
        model.addAttribute("categories", categories);
        return "mypage/scraps";
    }
}
