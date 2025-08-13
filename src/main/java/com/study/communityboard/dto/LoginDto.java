package com.study.communityboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank(message = "학번을 입력해주세요.")
    @Size(min = 10, max = 10, message = "학번은 10자리여야 합니다.")
    private String studentId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
