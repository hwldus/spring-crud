package com.assignment.SpringCRUD.controller;

import com.assignment.SpringCRUD.dto.BookDTO;
import com.assignment.SpringCRUD.model.Book;
import com.assignment.SpringCRUD.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name="Book API", description = "도서 관리 API")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @Operation(summary = "도서 생성", description = "새로운 도서를 생성합니다.")
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book savedBook = bookService.createBook(bookDTO);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping
    @Operation(summary = "모든 도서 조회", description = "모든 도서 목록을 조회합니다.")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 도서 조회", description = "특정 도서의 상세 정보를 조회합니다.")
    public ResponseEntity<BookDTO> getBookById(@Parameter(description = "조회할 도서 id", example = "1")@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookDTO bookDTO = BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .publication_date(book.getPublication_date())
                .author_id(book.getAuthor().getId())
                .build();
        return ResponseEntity.ok(bookDTO);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "도서 수정", description = "id로 특정 도서 정보를 수정합니다.")
    public ResponseEntity<Book> updateBook(@Parameter(description = "수정할 도서 id", example = "1")@PathVariable Long id,
                                           @Valid
                                           @Schema(description = "수정할 도서 정보",
                                                   example = "{ \"title\": \"노인\", \"description\": \"\", \"isbn\": \"1234567890\", \"publication_date\": \"\", \"author_id\": \"1\"}")
                                           @RequestBody BookDTO bookDTO) {
        Book updatedBook = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "도서 삭제", description = "id로 특정 도서를 삭제합니다. 이때 저자는 삭제되지 않습니다.")

    public ResponseEntity<String> deleteBook(@Parameter(description = "수정할 도서 id", example = "1")@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("도서가 삭제되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("도서를 찾을 수 없습니다.");
        }
    }
}
