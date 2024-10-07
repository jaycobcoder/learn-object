package com.example.object_procedure.persistence;

import com.example.object_procedure.domain.Reservation;

public interface ReservationDAO {
    void insert(Reservation reservation);
}