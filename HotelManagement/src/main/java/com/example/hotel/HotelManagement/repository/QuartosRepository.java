package com.example.hotel.HotelManagement.repository;

import com.example.hotel.HotelManagement.model.Quartos;
import com.example.hotel.HotelManagement.model.StatusQuarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartosRepository extends JpaRepository<Quartos, Long> {
    List<Quartos> findAllByStatus(StatusQuarto statusQuarto);
}
