package com.assignment.SpringCRUD.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false, unique = true)
    private String isbn;
    private LocalDate publication_date;
    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    @JsonBackReference  //순환 참조 방지
    private Author author;
}
