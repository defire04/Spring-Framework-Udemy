package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        return booksRepository.findById(id).map(Book::getOwner);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book -> book.setOwner(null));
    }


    @Transactional
    public void giveTo(int id, Person newOwner) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(newOwner);
            Date now = new Date();
            book.setReturnDate(new Date(now.getTime() + 10 * 24 * 60 * 60 * 1000));
        });
    }

    public List<Book> findByTitleStartingWith(String title) {
        return booksRepository.findByTitleStartingWith(title);
    }


    public List<Book> findWithPagination(int page, int bookPerPage, boolean sort) {
        if (sort) {
            return booksRepository.findAll(PageRequest.of(page, bookPerPage, Sort.by("year"))).getContent();
        } else {
            return booksRepository.findAll(PageRequest.of(page, bookPerPage)).getContent();
        }
    }

    //http://localhost:8080/books?page=1&books_per_page=2&sort_by_year=false
    public List<Book> sortingByYear(boolean sort) {
        if (sort) {
            return booksRepository.findAll(Sort.by("year"));
        } else {
            return booksRepository.findAll();
        }
    }
}
