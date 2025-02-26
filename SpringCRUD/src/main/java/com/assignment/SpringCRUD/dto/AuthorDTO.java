package com.assignment.SpringCRUD.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorDTO {
    @Schema(hidden = true)
    private Long id;
    @Schema(description = "저자 이름(필수)", example = "홍길동")
    private String name;
    @Schema(description = "저자 이메일(필수)", example = "hong@naver.com")
    private String email;
}
