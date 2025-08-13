package com.study.communityboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDto {

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotNull
    private Long boardId;

    @NotNull
    private Long userId;

}
