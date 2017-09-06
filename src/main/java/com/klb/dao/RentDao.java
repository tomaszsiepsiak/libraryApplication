package com.klb.dao;

import com.klb.entity.Rent;
import com.klb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentDao extends JpaRepository<Rent, Long> {

    //zwraca liste wypozyczen danego uzytkownika posortowana (od daty najwczesniejszej) ze wzgledu na date dodania wypozyczenia
    List<Rent> findByUserOrderByCreatedDateDesc(User user);
}
