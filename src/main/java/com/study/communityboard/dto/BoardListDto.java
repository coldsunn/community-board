package com.study.communityboard.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BoardListDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long categoryId;

    private String q;

}
