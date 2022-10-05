package com.kamil.bookslibrary.repository;

import com.kamil.bookslibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    @Query("SELECT AVG(rating) FROM Book")
    public double avg();


}
