package com.assignment.SpringCRUD.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BookDTO {
    @Schema(hidden = true)
    private Long id;
    @Schema(description = "책 제목(필수)", example = "노인과 바다")
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    @Schema(description = "책 설명(선택)", example = "쿠바 연안을 배경으로 늙은 어부 산티아고의 이야기를 그린 작품이다.")
    private String description;
    @Schema(description = "국제 표준 도서번호(필수)", example = "1234567890")
    private String isbn;
    @Schema(description = "출판일(선택)", example = "1952-09-01")
    private LocalDate publication_date;
    @Schema(description = "책을 쓴 저자 id(필수)", example = "1")
    @NotNull(message = "저자 ID는 필수입니다.")
    private Long author_id;
}
