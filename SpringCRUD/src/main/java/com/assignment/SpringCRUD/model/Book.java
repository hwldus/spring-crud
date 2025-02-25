package com.assignment.SpringCRUD.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false, unique = true)
    private String isbn;
    private LocalDate publicationDate;
    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private Author author;
}
