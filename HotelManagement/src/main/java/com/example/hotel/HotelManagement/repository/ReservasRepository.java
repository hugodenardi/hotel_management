package com.example.hotel.HotelManagement.repository;

import com.example.hotel.HotelManagement.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<Reserva, Long> {
}
