package com.example.object_procedure.persistence.memory;

import com.example.object_procedure.domain.Screening;
import com.example.object_procedure.persistence.ScreeningDAO;

public class ScreeningMemoryDAO extends InMemoryDAO<Screening> implements ScreeningDAO {

    @Override
    public Screening selectScreening(Long id) {
        return findOne(screening -> screening.getId().equals(id));
    }

}