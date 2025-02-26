package com.assignment.SpringCRUD.controller;

import com.assignment.SpringCRUD.dto.AuthorDTO;
import com.assignment.SpringCRUD.model.Author;
import com.assignment.SpringCRUD.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> creatAuthor(@RequestBody AuthorDTO authorDTO) {
        Author savedAuthor = authorService.createAuthor(authorDTO);
        return ResponseEntity.ok(savedAuthor);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName(), author.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        AuthorDTO authorDTO = AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .build();
        return ResponseEntity.ok(authorDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        Author updateAuthor = authorService.updateAuthor(id, authorDTO);
        AuthorDTO updateAuthorDTO = new AuthorDTO(updateAuthor.getName(), updateAuthor.getEmail());
        return ResponseEntity.ok(updateAuthorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok("해당 저자 및 관련된 도서가 삭제되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("저자를 찾을 수 없습니다.");
        }
    }
}
