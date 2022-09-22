package com.kamil.bookslibrary.controller;
import com.kamil.bookslibrary.model.Book;
import com.kamil.bookslibrary.model.BookDto;
import com.kamil.bookslibrary.service.BookService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
   public EntityModel<Book> getBook(@PathVariable Long id){                                                             //@PathVariable mapuje zmienna do parametru "{id}"
        return EntityModel.of(bookService.getBook(id),                                                                  //utworzone linki po wyświetleniu
                linkTo(methodOn(BookController.class).getBook(id)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAll()).withRel("book"),
                linkTo(BookController.class).slash(id).slash("activate")                    //do metody void aktywacja dostępności ksiązki
                        .withRel("activate"),
                linkTo(BookController.class).slash(id).slash("deactivate")
                        .withRel("deactivate")

        );
    }


    @PostMapping("")
    public EntityModel<Book> addBook(@RequestBody BookDto bookDto){
        Book book = bookService.addBook(new Book(
                EMPTY_ID,   //stała = null
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getPages(),
                bookDto.getRating(),
                bookDto.isAvailable()
        ));


        return EntityModel.of(book,                                                                  //utworzone linki po wyświetleniu
                linkTo(methodOn(BookController.class).getBook(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAll()).withRel("book"),
                linkTo(BookController.class).slash(book.getId()).slash("activate")                    //do metody void aktywacja dostępności ksiązki
                        .withRel("activate"),
                linkTo(BookController.class).slash(book.getId()).slash("deactivate")
                        .withRel("deactivate")

        );
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
                bookDto.isAvailable()
        ));


    }
//usuwanie książki o podanym id

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
         bookService.deleteBook(id);
    }

//zmienia status książki o podanym id z niedostępnej na dostępną
    @PutMapping("/{id}/activate")
    public void activateBook(@PathVariable Long id){
        bookService.activateBook(id);
    }

//zmiana statusu książki o podanym id z dostępnej na niedostępną
@PutMapping("/{id}/deactivate")
    public void deactivateBook(@PathVariable Long id){
        bookService.deactivateBook(id);
}

}
