package com.example.object_procedure.persistence.memory;

import com.example.object_procedure.domain.DiscountCondition;
import com.example.object_procedure.persistence.DiscountConditionDAO;
import java.util.List;

public class DiscountConditionMemoryDAO extends InMemoryDAO<DiscountCondition> implements
        DiscountConditionDAO {

    @Override
    public List<DiscountCondition> selectDiscountConditions(Long policyId) {
        return findMany(condition -> condition.getPolicyId().equals(policyId));
    }
}