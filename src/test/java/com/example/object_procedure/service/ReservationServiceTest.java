package com.example.object_procedure.service;

import static com.example.object_procedure.domain.DiscountCondition.ConditionType.PERIOD_CONDITION;
import static com.example.object_procedure.domain.DiscountCondition.ConditionType.SEQUENCE_CONDITION;
import static com.example.object_procedure.domain.DiscountPolicy.PolicyType.AMOUNT_POLICY;
import static com.example.object_procedure.domain.DiscountPolicy.PolicyType.PERCENT_POLICY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import com.example.object_procedure.domain.DiscountCondition;
import com.example.object_procedure.domain.DiscountPolicy;
import com.example.object_procedure.domain.Movie;
import com.example.object_procedure.domain.Reservation;
import com.example.object_procedure.domain.Screening;
import com.example.object_procedure.domain.TimeInterval;
import com.example.object_procedure.generic.Money;
import com.example.object_procedure.persistence.DiscountConditionDAO;
import com.example.object_procedure.persistence.DiscountPolicyDAO;
import com.example.object_procedure.persistence.MovieDAO;
import com.example.object_procedure.persistence.ReservationDAO;
import com.example.object_procedure.persistence.ScreeningDAO;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ScreeningDAO screeningDAO;
    @Mock
    private MovieDAO movieDAO;
    @Mock
    private DiscountPolicyDAO discountPolicyDAO;
    @Mock
    private DiscountConditionDAO discountConditionDAO;
    @Mock
    private ReservationDAO reservationDAO;

    @Test
    void 금액할인정책_계산() {
        // given
        Long customerId = 1L;
        Long screeningId = 1L;
        Long movieId = 1L;
        Long policyId = 1L;

        Mockito.when(screeningDAO.selectScreening(screeningId))
                .thenReturn(new Screening(screeningId, movieId, 1,
                        LocalDateTime.of(2024, 12, 11, 18, 0)));

        Mockito.when(movieDAO.selectMovie(movieId))
                .thenReturn(new Movie(movieId, "한신", 120, Money.wons(10000)));

        Mockito.when(discountPolicyDAO.selectDiscountPolicy(movieId))
                .thenReturn(new DiscountPolicy(policyId, movieId, AMOUNT_POLICY, Money.wons(1000),
                        null));

        Mockito.when(discountConditionDAO.selectDiscountConditions(policyId))
                .thenReturn(List.of(
                        new DiscountCondition(1L, policyId, SEQUENCE_CONDITION, null, null, 1),
                        new DiscountCondition(2L, policyId, SEQUENCE_CONDITION, null, null, 10),
                        new DiscountCondition(3L, policyId, PERIOD_CONDITION, MONDAY,
                                new TimeInterval(LocalTime.of(10, 12), LocalTime.of(12, 0)), null),
                        new DiscountCondition(4L, policyId, PERIOD_CONDITION, WEDNESDAY,
                                new TimeInterval(LocalTime.of(18, 0), LocalTime.of(21, 0)), null)));

        // when
        Reservation reservation = reservationService.reserveScreening(customerId, screeningId, 2);

        // then
        Assertions.assertEquals(reservation.getFee(), Money.wons(18000));
    }

    @Test
    void 비율할인정책_계산() {
        // given
        Long customerId = 1L;
        Long screeningId = 1L;
        Long movieId = 1L;
        Long policyId = 1L;

        Mockito.when(screeningDAO.selectScreening(screeningId))
                .thenReturn(new Screening(screeningId, movieId, 1,
                        LocalDateTime.of(2024, 12, 11, 18, 0)));

        Mockito.when(movieDAO.selectMovie(movieId))
                .thenReturn(new Movie(movieId, "한신", 120, Money.wons(10000)));

        Mockito.when(discountPolicyDAO.selectDiscountPolicy(movieId))
                .thenReturn(new DiscountPolicy(policyId, movieId, PERCENT_POLICY, null, 0.1));

        Mockito.when(discountConditionDAO.selectDiscountConditions(policyId))
                .thenReturn(List.of(
                        new DiscountCondition(1L, policyId, SEQUENCE_CONDITION, null, null,1),
                        new DiscountCondition(2L, policyId, SEQUENCE_CONDITION, null, null, 10),
                        new DiscountCondition(3L, policyId, PERIOD_CONDITION, MONDAY,
                                new TimeInterval(LocalTime.of(10, 12), LocalTime.of(12, 0)), null),
                        new DiscountCondition(4L, policyId, PERIOD_CONDITION, WEDNESDAY,
                                new TimeInterval(LocalTime.of(18, 0), LocalTime.of(21, 0)), null)));

        // when
        Reservation reservation = reservationService.reserveScreening(customerId, screeningId, 2);

        // then
        Assertions.assertEquals(reservation.getFee(), Money.wons(18000));
    }
}