package com.example.hotel.HotelManagement.repository;

import com.example.hotel.HotelManagement.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedesRepository extends JpaRepository<Hospede, Long> {
}
