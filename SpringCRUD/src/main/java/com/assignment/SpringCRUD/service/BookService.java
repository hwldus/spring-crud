package com.assignment.SpringCRUD.service;

import com.assignment.SpringCRUD.dto.BookDTO;
import com.assignment.SpringCRUD.isbn;
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
        if(!isbn.isValidISBN(bookDTO.getIsbn())) {
            throw new IllegalArgumentException("isbn이 유효하지 않습니다.");
        }
        if(bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new IllegalArgumentException("이미 존재하는 isbn입니다.");
        }
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
                .map(book -> BookDTO.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .isbn(book.getIsbn())
                        .publication_date(book.getPublication_date())
                        .author_id(book.getAuthor().getId())
                        .build())
                .collect(Collectors.toList());
    }

    //책 상세조회
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 id 입니다."));
    }

    //책 수정
    @Transactional
    public Book updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("도서를 찾을 수 없습니다."));
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        if(!isbn.isValidISBN(bookDTO.getIsbn())) {
            throw new IllegalArgumentException("isbn이 유효하지 않습니다.");
        }
        book.setIsbn(bookDTO.getIsbn());
        book.setPublication_date(bookDTO.getPublication_date());
        if (bookDTO.getAuthor_id() != null) {
            Author author = authorRepository.findById(bookDTO.getAuthor_id())
                    .orElseThrow(() -> new IllegalStateException("저자를 찾을 수 없습니다."));
            book.setAuthor(author);
        }
        return bookRepository.save(book);
    }

    //책 삭제
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("도서를 찾을 수 없습니다."));
        bookRepository.delete(book);
    }
}
