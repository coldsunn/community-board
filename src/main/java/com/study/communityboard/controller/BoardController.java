package com.study.communityboard.controller;

import com.study.communityboard.domain.*;
import com.study.communityboard.dto.BoardDto;
import com.study.communityboard.dto.BoardListDto;
import com.study.communityboard.dto.CommentDto;
import com.study.communityboard.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;
    private final BoardLikeService boardLikeService;
    private final BoardScrapService boardScrapService;
    private final CommentService commentService;

    @GetMapping("/boards")
    public String list(@Valid @ModelAttribute BoardListDto boardListDto,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            return "redirect:/auth/login";
        }

        User user = userService.getProfile(boardListDto.getUserId());
        if (user == null) {
            return "redirect:/auth/login";
        }

        Category category = boardService.getCategory(boardListDto.getCategoryId());

        List<Board> boards = (boardListDto.getQ() != null && !boardListDto.getQ().isBlank()) ?
                boardService.search(user.getDepartment(), boardListDto.getCategoryId(), boardListDto.getQ()) :
                boardService.listAll(user.getDepartment(), boardListDto.getCategoryId());

        List<Category> categories = boardService.getAllCategories();

        model.addAttribute("user", user);
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("boards", boards);
        return "boards/list";
    }

    @GetMapping("/boards/new")
    public String newForm(@ModelAttribute BoardListDto boardListDto,
                          Model model) {
        User user = userService.getProfile(boardListDto.getUserId());
        if (user == null) {
            return "redirect:/auth/login";
        }

        Category category = boardService.getCategory(boardListDto.getCategoryId());
        List<Category> categories = boardService.getAllCategories();

        model.addAttribute("user", user);
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("boardDto", new BoardDto());
        return "boards/new";
    }

    @PostMapping("/boards/new")
    public String create(@Valid @ModelAttribute("boardDto") BoardDto boardDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "제목과 내용을 모두 입력해주세요.");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.boardRequestDto", bindingResult);
            return "redirect:/boards/new?userId=" + boardDto.getUserId() + "&categoryId=" + boardDto.getCategoryId();
        }

        Long newBoardId = boardService.create(
                boardDto.getUserId(),
                boardDto.getCategoryId(),
                boardDto.getTitle(),
                boardDto.getContent()
        );

        redirectAttributes.addAttribute("userId", boardDto.getUserId());
        return "redirect:/boards/" + newBoardId;
    }

    @GetMapping("/boards/{boardId}")
    public String detail(@PathVariable("boardId") Long boardId,
                         @RequestParam("userId") Long userId,
                         HttpSession session,
                         Model model) {
        User user = userService.getProfile(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        String sessionKey = "view_board_" + boardId;

        if (session.getAttribute(sessionKey) == null) {
            boardService.increaseView(boardId);
            session.setAttribute(sessionKey, true);
        }

        Board board = boardService.getBoard(boardId);
        List<Category> categories = boardService.getAllCategories();
        List<Comment> comments = commentService.listByBoard(boardId);

        boolean isLiked = boardLikeService.check(boardId, userId);
        boolean isScrapped = boardScrapService.check(boardId, userId);

        model.addAttribute("user", user);
        model.addAttribute("board", board);
        model.addAttribute("categories", categories);
        model.addAttribute("comments", comments);
        model.addAttribute("isLiked", isLiked);
        model.addAttribute("isScrapped", isScrapped);
        model.addAttribute("commentDto", new CommentDto());

        return "boards/detail";
    }

    @GetMapping("/boards/{boardId}/edit")
    public String editForm(@PathVariable("boardId") Long boardId,
                           @RequestParam("userId") Long userId,
                           Model model) {
        User user = userService.getProfile(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        Board board = boardService.getBoard(boardId);
        List<Category> categories = boardService.getAllCategories();

        model.addAttribute("user", user);
        model.addAttribute("board", board);
        model.addAttribute("categories", categories);
        return "boards/edit";
    }

    @PostMapping("/boards/{boardId}/edit")
    public String update(@PathVariable("boardId") Long boardId,
                         @Valid @ModelAttribute("boardDto") BoardDto boardDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "제목과 내용을 모두 입력해주세요.");
            model.addAttribute("user", userService.getProfile(boardDto.getUserId()));
            model.addAttribute("board", boardService.getBoard(boardId));
            model.addAttribute("categories", boardService.getAllCategories());
            return "boards/edit";
        }

        boardService.update(boardId, boardDto.getTitle(), boardDto.getContent());

        redirectAttributes.addAttribute("userId", boardDto.getUserId());
        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/boards/{boardId}/like")
    public String toggleLike(@PathVariable("boardId") Long boardId,
                             @RequestParam("userId") Long userId,
                             RedirectAttributes redirectAttributes) {
        boardLikeService.toggle(boardId, userId);
        redirectAttributes.addAttribute("userId", userId);
        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/boards/{boardId}/scrap")
    public String toggleScrap(@PathVariable("boardId") Long boardId,
                              @RequestParam("userId") Long userId,
                              RedirectAttributes redirectAttributes) {
        boardScrapService.toggle(boardId, userId);
        redirectAttributes.addAttribute("userId", userId);
        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/boards/{boardId}/delete")
    public String delete(@PathVariable("boardId") Long boardId,
                         @RequestParam("userId") Long userId,
                         RedirectAttributes redirectAttributes) {

        Board boardToDelete = boardService.getBoard(boardId);

        boardService.delete(boardId);

        redirectAttributes.addAttribute("userId", userId);
        redirectAttributes.addAttribute("categoryId", boardToDelete.getCategory().getId());
        return "redirect:/boards";
    }

    @GetMapping("/boards/search")
    public String search(@RequestParam("userId") Long userId,
                         @RequestParam("categoryId") Long categoryId,
                         @RequestParam("q") String keyword,
                         RedirectAttributes redirectAttributes) {

        User user = userService.getProfile(userId);
        if (user == null) {
            return "redirect:/auth/login";
        }

        Category category = boardService.getCategory(categoryId);

        redirectAttributes.addAttribute("userId", user.getId());
        redirectAttributes.addAttribute("categoryId", category.getId());
        redirectAttributes.addAttribute("q", keyword);
        return "redirect:/boards";
    }

}
