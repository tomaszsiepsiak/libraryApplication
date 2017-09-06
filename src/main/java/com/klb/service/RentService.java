package com.klb.service;

import com.klb.entity.Book;
import com.klb.entity.Rent;
import com.klb.entity.User;

import java.util.List;

public interface RentService {
    void createRent(User user, Book book);
    List<Rent> findAll();
    List<Rent> findByUserOrderByCreatedDateDesc(User user);
}
