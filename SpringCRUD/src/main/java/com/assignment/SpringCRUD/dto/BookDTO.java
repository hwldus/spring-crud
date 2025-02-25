package com.assignment.SpringCRUD.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private String description;
    private String isbn;
    private LocalDate publication_date;
    private Long author_id;

    public BookDTO(Long id, String title, String description, String isbn, LocalDate publication_date, Long author_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.publication_date = publication_date;
        this.author_id = author_id;
    }
}
