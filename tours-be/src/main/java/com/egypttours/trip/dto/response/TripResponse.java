package com.egypttours.trip.dto.response;

import com.egypttours.trip.domain.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripResponse {
    private Long id;
    private String title;
    private String description;
    private String destination;
    private Integer durationInDays;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maxTravelers;
    private TripStatus status;
    private boolean featured;
}
