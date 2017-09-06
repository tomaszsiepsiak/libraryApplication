package com.klb.dao;

import com.klb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookDao extends JpaRepository<Book, Long> {
}
