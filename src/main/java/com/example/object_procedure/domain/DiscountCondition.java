package com.example.object_procedure.domain;

import java.time.DayOfWeek;

public class DiscountCondition {

    public enum ConditionType {
        PERIOD_CONDITION, SEQUENCE_CONDITION, COMBINED_CONDITION
    }

    private Long id;
    private Long policyId;
    private ConditionType conditionType;
    private DayOfWeek dayOfWeek;
    private TimeInterval interval;
    private Integer sequence;

    public DiscountCondition() {
    }

    public DiscountCondition(Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek,
            TimeInterval interval, Integer sequence) {
        this(null, policyId, conditionType, dayOfWeek, interval, sequence);
    }

    public DiscountCondition(Long id, Long policyId, ConditionType conditionType,
            DayOfWeek dayOfWeek, TimeInterval interval, Integer sequence) {
        this.id = id;
        this.policyId = policyId;
        this.conditionType = conditionType;
        this.dayOfWeek = dayOfWeek;
        this.interval = interval;
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(final Screening screening) {
        if (isPeriodCondition()) {
            if (screening.isPlayedIn(
                    this.dayOfWeek,
                    this.interval.getStartTime(),
                    this.interval.getEndTime())
            ) {
                return true;
            }
        } else if (isSequenceCondition()) {
            if (this.sequence.equals(screening.getSequence())) {
                return true;
            }
        } else if (isCombinedCondition()) {
            if ((this.sequence.equals(screening.getSequence())) && (screening.isPlayedIn(
                    this.dayOfWeek, this.interval.getStartTime(), this.interval.getEndTime()))) {
                return true;
            }
        }
        return false;
    }

    public Long getPolicyId() {
        return policyId;
    }

    private boolean isPeriodCondition() {
        return ConditionType.PERIOD_CONDITION.equals(conditionType);
    }

    private boolean isSequenceCondition() {
        return ConditionType.SEQUENCE_CONDITION.equals(conditionType);
    }

    private boolean isCombinedCondition() {
        return ConditionType.COMBINED_CONDITION.equals(conditionType);
    }
}