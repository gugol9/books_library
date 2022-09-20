package com.kamil.bookslibrary.controller;
import com.kamil.bookslibrary.model.Book;
import com.kamil.bookslibrary.model.BookDto;
import com.kamil.bookslibrary.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private static final Long EMPTY_ID = null;
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //return book
    @GetMapping("")
   public List<Book> getAll(){
        return bookService.getBooks();
    }

    //Zwraca książkę o podanym id
    @GetMapping("/{id}")
   public Book getBook(@PathVariable Long id){ //@PathVariable mapuje zmienna do parametru "{id}"
        return bookService.getBook(id);
    }


    @PostMapping("")
    public Book addBook(@RequestBody BookDto bookDto){
        return bookService.addBook(new Book(
                EMPTY_ID,   //stała = null
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getPages(),
                bookDto.getRating(),
                bookDto.isIsavalible()
        ));
    }
    //Stworzona klasa BookDto bez pola ID,  żeby było wiadomo które id ma w parametrze wziążć pod uwage, bo klasa Book to encja
    @PutMapping("/{id}") //dodaje mapping bo chce edytowac konkretną ksiąze po id
    public Book editBook(@PathVariable Long id, @RequestBody BookDto bookDto){
        return bookService.editBook(new Book(
                id,
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getPages(),
                bookDto.getRating(),
                bookDto.isIsavalible()
        ));

    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
         bookService.deleteBook(id);
    }

}
