package com.study.communityboard.controller;

import com.study.communityboard.dto.CommentDto;
import com.study.communityboard.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 추가
    @PostMapping("/boards/{boardId}/comments")
    public String addComment(@PathVariable("boardId") Long boardId,
                             @Valid @ModelAttribute CommentDto commentDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "내용을 입력해주세요.");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentDto", bindingResult);
            redirectAttributes.addAttribute("userId", commentDto.getUserId());
            return "redirect:/boards/" + boardId;
        }

        commentService.add(commentDto.getBoardId(), commentDto.getUserId(), commentDto.getContent());
        redirectAttributes.addAttribute("userId", commentDto.getUserId());
        return "redirect:/boards/" + boardId;
    }

    // 댓글 삭제
    @PostMapping("/comments/{commentId}/delete")
    public String deleteComment(@PathVariable("commentId") Long commentId,
                                @Valid @ModelAttribute CommentDto commentDto,
                                RedirectAttributes redirectAttributes) {
        commentService.delete(commentId);
        redirectAttributes.addAttribute("userId", commentDto.getUserId());
        return "redirect:/boards/" + commentDto.getBoardId();
    }
}
