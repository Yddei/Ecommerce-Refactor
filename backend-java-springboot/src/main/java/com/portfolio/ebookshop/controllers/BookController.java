package com.portfolio.ebookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.portfolio.ebookshop.models.Book;
import com.portfolio.ebookshop.repos.BookRepository;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    //get all
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    //get by searched if implemented
    /*@GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword); 
    }
    */
}