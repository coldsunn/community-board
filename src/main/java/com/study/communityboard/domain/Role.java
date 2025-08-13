package com.study.communityboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("일반"), COUNCIL("학생회");

    private final String displayName;
}
