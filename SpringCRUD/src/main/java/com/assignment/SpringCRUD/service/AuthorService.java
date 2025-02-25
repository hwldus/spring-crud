package com.assignment.SpringCRUD.service;

import com.assignment.SpringCRUD.dto.AuthorDTO;
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

    // 저자 한명 저장
    @Transactional
    public Author createAuthor(AuthorDTO authorDTO) {
        if(authorDTO.getName() == null || authorDTO.getName().trim().isEmpty()) {
            throw new IllegalStateException("이름은 필수 입력입니다.");
        }
        if(authorRepository.findByEmail(authorDTO.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
        Author author = new Author(authorDTO.getName(), authorDTO.getEmail());
        return authorRepository.save(author);
    }

    //모든 저자 검색
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    //저자 수정
    @Transactional
    public Author updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalStateException("저자를 찾을 수 없습니다."));
        if(authorDTO.getName() != null && !authorDTO.getName().trim().isEmpty()) {
            author.setName(authorDTO.getName());
        }
        if (authorDTO.getEmail() != null && !authorDTO.getEmail().trim().isEmpty()) {
            if (authorRepository.findByEmail(authorDTO.getEmail()).isPresent()) {
                throw new IllegalStateException("이미 존재하는 이메일입니다.");
            }
            author.setEmail(authorDTO.getEmail());
        }
        return authorRepository.save(author);
    }

}
