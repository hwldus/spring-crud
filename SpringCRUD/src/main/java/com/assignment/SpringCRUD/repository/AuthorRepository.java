package com.assignment.SpringCRUD.repository;

import com.assignment.SpringCRUD.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email); //중복체크
}
