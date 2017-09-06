package com.klb.service;

import com.klb.entity.Book;
import com.klb.entity.User;

import java.util.List;


public interface BookService {
    void save(Book book);
    List<Book> findAll();
    void delete(Long id);
    Book findOne(Long id);
}
