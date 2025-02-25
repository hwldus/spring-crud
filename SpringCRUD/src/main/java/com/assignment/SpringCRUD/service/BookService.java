package com.assignment.SpringCRUD.service;

import com.assignment.SpringCRUD.dto.BookDTO;
import com.assignment.SpringCRUD.model.Author;
import com.assignment.SpringCRUD.model.Book;
import com.assignment.SpringCRUD.repository.AuthorRepository;
import com.assignment.SpringCRUD.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    //책 저장
    @Transactional
    public Book createBook(BookDTO bookDTO) {
        if (bookDTO.getAuthor_id() == null) {
            throw new IllegalArgumentException("author_id는 필수입니다.");
        }
        Author author = authorRepository.findById(bookDTO.getAuthor_id())
                .orElseThrow(() -> new IllegalStateException("저자를 찾을 수 없습니다."));
        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .description(bookDTO.getDescription())
                .isbn(bookDTO.getIsbn())
                .publication_date(bookDTO.getPublication_date())
                .author(author)
                .build();
        return bookRepository.save(book);
    }

    //책 조회
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getDescription(),
                        book.getIsbn(),
                        book.getPublication_date(),
                        book.getAuthor().getId()))
                .collect(Collectors.toList());
    }
}
