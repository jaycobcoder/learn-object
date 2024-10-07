package com.example.object_procedure.persistence;

import com.example.object_procedure.domain.DiscountCondition;
import java.util.List;

public interface DiscountConditionDAO {
    List<DiscountCondition> selectDiscountConditions(Long policyId);

    void insert(DiscountCondition discountCondition);
}