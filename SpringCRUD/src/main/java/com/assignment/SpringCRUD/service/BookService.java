package com.assignment.SpringCRUD.service;

import com.assignment.SpringCRUD.dto.BookDTO;
import com.assignment.SpringCRUD.model.Author;
import com.assignment.SpringCRUD.model.Book;
import com.assignment.SpringCRUD.repository.AuthorRepository;
import com.assignment.SpringCRUD.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    //책 상세조회
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 id 입니다."));
    }

    //책 수정
    @Transactional
    public Book updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("책을 찾을 수 없습니다."));
        if (bookDTO.getTitle() != null) book.setTitle(bookDTO.getTitle());
        if (bookDTO.getDescription() != null) book.setDescription(bookDTO.getDescription());
        if (bookDTO.getIsbn() != null) book.setIsbn(bookDTO.getIsbn());
        if (bookDTO.getPublication_date() != null) book.setPublication_date(bookDTO.getPublication_date());
        if (bookDTO.getAuthor_id() != null) {
            Optional<Author> author = authorRepository.findById(bookDTO.getAuthor_id());
            author.ifPresent(book::setAuthor);
        }
        return bookRepository.save(book);
    }

    //책 삭제
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("책을 찾을 수 없습니다."));
        bookRepository.delete(book);
    }
}
