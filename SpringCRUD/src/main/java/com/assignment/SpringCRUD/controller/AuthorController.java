package com.assignment.SpringCRUD.controller;

import com.assignment.SpringCRUD.dto.AuthorDTO;
import com.assignment.SpringCRUD.model.Author;
import com.assignment.SpringCRUD.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Tag(name="Author API", description = "저자 관리 API")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @Operation(summary = "저자 생성", description = "새로운 저자를 생성합니다.")
    public ResponseEntity<Author> creatAuthor(@RequestBody AuthorDTO authorDTO) {
        Author savedAuthor = authorService.createAuthor(authorDTO);
        return ResponseEntity.ok(savedAuthor);
    }

    @GetMapping
    @Operation(summary = "모든 저자 조회", description = "모든 저자 목록을 조회합니다.")
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 저자 조회", description = "특정 저자의 상세 정보를 조회합니다.")
    public ResponseEntity<AuthorDTO> getAuthorById(@Parameter(description = "조회할 저자 id", example = "1") @PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        AuthorDTO authorDTO = AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .build();
        return ResponseEntity.ok(authorDTO);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "저자 수정", description = "id로 특정 저자 정보를 수정합니다.")
    public ResponseEntity<Author> updateAuthor(@Parameter(description = "수정할 저자 id", example = "1") @PathVariable Long id,
                                               @Schema(description = "수정할 저자 정보", example = "{ \"name\": \"유재석\", \"email\": \"yoo@naver.com\" }")
                                               @RequestBody AuthorDTO authorDTO) {
        Author updateAuthor = authorService.updateAuthor(id, authorDTO);
        return ResponseEntity.ok(updateAuthor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "저자 삭제", description = "id로 특정 저자를 삭제합니다. 이때 저자가 쓴 도서 목록도 삭제 됩니다.")
    public ResponseEntity<String> deleteAuthor(@Parameter(description = "수정할 저자 id", example = "1") @PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok("해당 저자 및 관련된 도서가 삭제되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("저자를 찾을 수 없습니다.");
        }
    }
}
