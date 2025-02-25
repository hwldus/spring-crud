package com.assignment.SpringCRUD.service;

import com.assignment.SpringCRUD.model.Author;
import com.assignment.SpringCRUD.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author createAuthor(Author author) {
        if(author.getName() == null || author.getName().trim().isEmpty()) {
            throw new IllegalStateException("이름은 필수 입력입니다.");
        }
        if(authorRepository.findByEmail(author.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
        return authorRepository.save(author);
    }
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
