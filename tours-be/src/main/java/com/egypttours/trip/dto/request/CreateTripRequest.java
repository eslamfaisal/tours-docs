package com.egypttours.trip.dto.request;

import com.egypttours.common.constants.Messages;
import jakarta.validation.constraints.*;
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
public class CreateTripRequest {

    @NotBlank(message = Messages.Validation.TITLE_REQUIRED)
    private String title;

    @NotBlank(message = Messages.Validation.DESCRIPTION_REQUIRED)
    private String description;

    @NotBlank(message = Messages.Validation.DESTINATION_REQUIRED)
    private String destination;

    @NotNull(message = Messages.Validation.DURATION_REQUIRED)
    @Min(value = 1, message = Messages.Validation.DURATION_MIN)
    private Integer durationInDays;

    @NotNull(message = Messages.Validation.PRICE_REQUIRED)
    @DecimalMin(value = "0.0", message = Messages.Validation.PRICE_POSITIVE)
    private BigDecimal price;

    @NotNull(message = Messages.Validation.START_DATE_REQUIRED)
    private LocalDate startDate;

    @NotNull(message = Messages.Validation.END_DATE_REQUIRED)
    private LocalDate endDate;

    @NotNull(message = Messages.Validation.MAX_TRAVELERS_REQUIRED)
    @Min(value = 1, message = Messages.Validation.MAX_TRAVELERS_MIN)
    private Integer maxTravelers;
}
