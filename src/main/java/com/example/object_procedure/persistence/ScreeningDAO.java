package com.example.object_procedure.persistence;

import com.example.object_procedure.domain.Screening;

public interface ScreeningDAO {
    Screening selectScreening(Long screeningId);

    void insert(Screening screening);
}