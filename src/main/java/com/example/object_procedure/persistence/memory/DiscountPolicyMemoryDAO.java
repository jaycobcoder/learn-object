package com.example.object_procedure.persistence.memory;

import com.example.object_procedure.domain.DiscountPolicy;
import com.example.object_procedure.persistence.DiscountPolicyDAO;

public class DiscountPolicyMemoryDAO extends InMemoryDAO<DiscountPolicy> implements
        DiscountPolicyDAO {

    @Override
    public DiscountPolicy selectDiscountPolicy(Long movieId) {
        return findOne(policy -> policy.getMovieId().equals(movieId));
    }
}