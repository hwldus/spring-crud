package com.assignment.SpringCRUD.controller;

import com.assignment.SpringCRUD.dto.BookDTO;
import com.assignment.SpringCRUD.model.Book;
import com.assignment.SpringCRUD.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
        Book savedBook = bookService.createBook(bookDTO);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

}
