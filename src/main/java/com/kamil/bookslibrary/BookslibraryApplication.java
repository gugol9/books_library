package com.kamil.bookslibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BookslibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookslibraryApplication.class, args);
	}

}
