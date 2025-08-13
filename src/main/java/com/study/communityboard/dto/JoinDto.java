package com.study.communityboard.dto;

import com.study.communityboard.domain.Department;
import com.study.communityboard.domain.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JoinDto {

    @NotBlank(message = "학번을 입력해주세요.")
    @Size(min = 10, max = 10, message = "학번은 10자리여야 합니다.")
    private String studentId;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotNull(message = "학과를 입력해주세요.")
    private Department department;

    @NotNull(message = "역할을 입력해주세요.")
    private Role role;

}
