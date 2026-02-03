package com.egypttours.trip.controller;

import com.egypttours.common.dto.ApiResponse;
import com.egypttours.common.swagger.SwaggerExamples;
import com.egypttours.trip.dto.response.TripResponse;
import com.egypttours.trip.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
@Tag(name = "Public Trip API", description = "Public APIs for browsing trips")
public class PublicTripController {

    private final TripService tripService;

    @GetMapping
    @Operation(summary = "List all published trips", description = "Get a list of all active and published trips available for booking.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponse.class), examples = @ExampleObject(value = SwaggerExamples.Trip.TRIP_LIST_RESPONSE)))
    })
    public ResponseEntity<ApiResponse<List<TripResponse>>> getAllPublishedTrips() {
        return ResponseEntity.ok(ApiResponse.success(tripService.getPublishedTrips()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get trip details", description = "Get detailed information about a specific published trip.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponse.class), examples = @ExampleObject(value = SwaggerExamples.Trip.TRIP_DETAIL_RESPONSE))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trip not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.AdminTrip.TRIP_NOT_FOUND_RESPONSE)))
    })
    public ResponseEntity<ApiResponse<TripResponse>> getTripById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(tripService.getPublishedTripById(id)));
    }
}
