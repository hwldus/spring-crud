package com.assignment.SpringCRUD.repository;

import com.assignment.SpringCRUD.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
}
