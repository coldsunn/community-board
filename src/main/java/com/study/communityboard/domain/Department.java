package com.study.communityboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Department {
    BUSINESS_ADMIN("경영학과"),

    KOR_LIT("국어국문학과"),
    ENG_LIT("영어영문학과"),
    PHILOSOPHY("철학과"),
    KOR_HISTORY("한국사학과"),
    HISTORY("사학과"),
    SOCIOLOGY("사회학과"),
    GER_LIT("독어독문학과"),
    FRE_LIT("불어불문학과"),
    CHI_LIT("중어중문학과"),
    RUS_LIT("노어노문학과"),
    JAP_LIT("일어일문학과"),
    SPAN_LIT("서어서문학과"),
    HAC_LIT("한문학과"),
    LINGUISTICS("언어학과"),

    LIFE_SCIENCE("생명과학부"),
    LIFE_BIOTECH("생명공학부"),
    FOOD_BIOTECH("식품공학과"),
    ENVIRONMENTAL_SCIENCE("환경생태공학부"),
    FOOD_RESOURCE_ECONOMICS("식품자원경제학과"),

    POLITICAL_SCIENCE("정치외교학과"),
    ECONOMICS("경제학과"),
    STATISTICS("통계학과"),
    PUBLIC_ADMIN("행정학과"),

    MATHEMATICS("수학과"),
    PHYSICS("물리학과"),
    CHEMISTRY("화학과"),
    EARTH_ENVIRON("지구환경과학과"),

    CHEMICAL_ENGINEERING("화공생명공학부"),
    NEW_MATERIALS("신소재공학부"),
    CIVIL_ENGINEERING("건축사회환경공학부"),
    ARCHITECTURE("건축학과"),
    MECHANICAL_ENGINEERING("기계공학부"),
    INDUSTRIAL_ENGINEERING("산업경영공학부"),
    ELECTRICAL_ENGINEERING("전기전자공학부"),
    SEMI_CONDUCTOR("반도체공학과"),
    FUSION_ENERGY("융합에너지공학과"),
    NEXT_GEN_COMM("차세대통신학과"),

    PRE_MEDICINE("의예과"),
    MEDICINE("의학과"),

    EDUCATION("교육학과"),
    PHYSICAL_EDUCATION("체육교육과"),
    HOME_ECONOMICS_EDUCATION("가정교육과"),
    MATH_EDUCATION("수학교육과"),
    KOREAN_EDUCATION("국어교육과"),
    ENGLISH_EDUCATION("영어교육과"),
    GEOGRAPHY_EDUCATION("지리교육과"),
    HISTORY_EDUCATION("역사교육과"),

    NURSING("간호학과"),

    COMPUTER_SCIENCE("컴퓨터학과"),
    DATA_SCIENCE("데이터과학과"),
    AI("인공지능학과"),

    DESIGN("디자인조형학부"),

    INTERNATIONAL_STUDIES("국제학부"),
    GLOBAL_KOREA("글로벌한국융합학부"),
    GLOBAL_LIBERAL("글로벌자율학부"),

    MEDIA("미디어학부"),
    GLOBAL_ENTERTAINMENT("글로벌엔터테인먼트학부"),

    BIO_MEDICAL("바이오의공학부"),
    BIO_SYSTEM_SCIENCE("바이오시스템의과학부"),
    HEALTH_ENV_SCIENCE("보건환경융합과학부"),
    HEALTH_POLICY("보건정책관리학부"),

    LIBERAL_ARTS("자유전공학부"),

    CYBER_DEFENSE("사이버국방학과"),
    SMART_SECURITY("스마트보안학부"),

    PSYCHOLOGY("심리학부"),

    SMART_MOBILITY("스마트모빌리티학부");

    private final String displayName;

}