package com.example.object_procedure.persistence.memory;

import com.example.object_procedure.domain.Reservation;
import com.example.object_procedure.persistence.ReservationDAO;

public class ReservationMemoryDAO extends InMemoryDAO<Reservation> implements ReservationDAO {
    @Override
    public void insert(Reservation reservation) {
        super.insert(reservation);
    }
}