package com.kamil.bookslibrary.model;

import com.kamil.bookslibrary.model.Book;
import com.kamil.bookslibrary.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;    //potrzebuje bazy danych wiec tworze obiekt z interfejsu bookRepository

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow();//metoda findById zwraca OPTIONALA i dodac trzeba orElseThrow() zwraca blad jak nie znajdzie
    }

    public Book addBook(Book book){
    return bookRepository.save(book);
    }


    public Book editBook(Book book) {
        return bookRepository.save(book);
    }


    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}