package com.assignment.SpringCRUD.dto;

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
    private Long id;
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    private String description;
    private String isbn;
    private LocalDate publication_date;
    @NotNull(message = "저자 ID는 필수입니다.")
    private Long author_id;
}
