package com.kamil.bookslibrary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

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

    //Add book
    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.registerBook(book);
    }


}
