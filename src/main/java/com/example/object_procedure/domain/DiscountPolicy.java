package com.example.object_procedure.domain;

import com.example.object_procedure.generic.Money;

public class DiscountPolicy {

    public enum PolicyType {PERCENT_POLICY, AMOUNT_POLICY}

    private Long id;
    private Long movieId;
    private PolicyType policyType;
    private Money amount;
    private Double percent;

    public DiscountPolicy() {
    }

    public DiscountPolicy(Long movieId, PolicyType policyType, Money amount, Double percent) {
        this(null, movieId, policyType, amount, percent);
    }

    public DiscountPolicy(Long id, Long movieId, PolicyType policyType, Money amount,
            Double percent) {
        this.id = id;
        this.movieId = movieId;
        this.policyType = policyType;
        this.amount = amount;
        this.percent = percent;
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Money calculateDiscount(Movie movie) {
        if (isAmountPolicy()) {
            return this.amount;
        } else if (isPercentPolicy()) {
            return movie.getFee().times(this.percent);
        }

        return Money.ZERO;
    }

    public boolean isAmountPolicy() {
        return PolicyType.AMOUNT_POLICY.equals(policyType);
    }

    public boolean isPercentPolicy() {
        return PolicyType.PERCENT_POLICY.equals(policyType);
    }
}