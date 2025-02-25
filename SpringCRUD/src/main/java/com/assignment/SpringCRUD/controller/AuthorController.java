package com.assignment.SpringCRUD.controller;

import com.assignment.SpringCRUD.dto.AuthorDTO;
import com.assignment.SpringCRUD.model.Author;
import com.assignment.SpringCRUD.service.AuthorService;
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
}
