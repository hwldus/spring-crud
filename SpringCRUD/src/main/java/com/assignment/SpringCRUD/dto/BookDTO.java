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

    public BookDTO() {
    }
    public BookDTO(Long id, String title, String description, String isbn, LocalDate publication_date, Long author_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.publication_date = publication_date;
        this.author_id = author_id;
    }
    public BookDTO(String title, String description, String isbn, LocalDate publication_date, Long author_id) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.publication_date = publication_date;
        this.author_id = author_id;
    }
}
