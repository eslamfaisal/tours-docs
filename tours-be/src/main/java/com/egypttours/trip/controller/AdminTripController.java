package com.egypttours.trip.controller;

import com.egypttours.common.constants.Messages;
import com.egypttours.common.dto.ApiResponse;
import com.egypttours.common.swagger.SwaggerExamples;
import com.egypttours.trip.dto.request.CreateTripRequest;
import com.egypttours.trip.dto.request.UpdateTripRequest;
import com.egypttours.trip.dto.response.TripResponse;
import com.egypttours.trip.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/trips")
@RequiredArgsConstructor
@Tag(name = "Admin Trip Management", description = "APIs for managing trips (Admin only)")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPER')")
public class AdminTripController {

        private final TripService tripService;

        @GetMapping
        @Operation(summary = "Get all trips", description = "Retrieve a list of all trips (including DRAFT, PUBLISHED, ARCHIVED).")
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Trips retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponse.class), examples = @ExampleObject(value = SwaggerExamples.Trip.TRIP_LIST_RESPONSE))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized - Not authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.Common.UNAUTHORIZED_RESPONSE))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - Not an admin", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.Common.FORBIDDEN_RESPONSE)))
        })
        public ResponseEntity<ApiResponse<List<TripResponse>>> getAllTrips() {
                List<TripResponse> trips = tripService.getAllTrips();
                return ResponseEntity.ok(ApiResponse.success(Messages.Success.DEFAULT, trips));
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get trip by ID", description = "Retrieve details of a specific trip by its ID (Admin access).")
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Trip retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponse.class), examples = @ExampleObject(value = SwaggerExamples.Trip.TRIP_DETAIL_RESPONSE))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trip not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.AdminTrip.TRIP_NOT_FOUND_RESPONSE)))
        })
        public ResponseEntity<ApiResponse<TripResponse>> getTripById(@PathVariable Long id) {
                TripResponse trip = tripService.getTripById(id);
                return ResponseEntity.ok(ApiResponse.success(Messages.Success.DEFAULT, trip));
        }

        @PostMapping
        @Operation(summary = "Create a new trip", description = "Create a new trip in DRAFT status. Only accessible by ADMIN users.")
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Trip created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponse.class), examples = @ExampleObject(value = SwaggerExamples.AdminTrip.CREATE_TRIP_RESPONSE_SUCCESS))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.Common.VALIDATION_ERROR_RESPONSE))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized - Not authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.Common.UNAUTHORIZED_RESPONSE))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - Not an admin", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.Common.FORBIDDEN_RESPONSE)))
        })
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Trip details to create", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateTripRequest.class), examples = @ExampleObject(value = SwaggerExamples.AdminTrip.CREATE_TRIP_REQUEST)))
        public ResponseEntity<ApiResponse<TripResponse>> createTrip(
                        @Valid @RequestBody CreateTripRequest request) {
                TripResponse trip = tripService.createTrip(request);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(Messages.Success.TRIP_CREATED, trip));
        }

        @PutMapping("/{id}")
        @PreAuthorize("hasAnyRole('ADMIN', 'SUPER')")
        @Operation(summary = "Update a trip", description = "Update an existing trip. Can also be used to publish/unpublish trips.")
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Trip updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponse.class), examples = @ExampleObject(value = SwaggerExamples.AdminTrip.CREATE_TRIP_RESPONSE_SUCCESS))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.Common.VALIDATION_ERROR_RESPONSE))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trip not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.AdminTrip.TRIP_NOT_FOUND_RESPONSE)))
        })
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Trip details to update", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateTripRequest.class), examples = @ExampleObject(value = SwaggerExamples.AdminTrip.UPDATE_TRIP_REQUEST)))
        public ResponseEntity<ApiResponse<TripResponse>> updateTrip(
                        @PathVariable Long id,
                        @Valid @RequestBody UpdateTripRequest request) {
                TripResponse trip = tripService.updateTrip(id, request);
                return ResponseEntity.ok(ApiResponse.success(Messages.Success.TRIP_UPDATED, trip));
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasAnyRole('ADMIN', 'SUPER')")
        @Operation(summary = "Delete a trip", description = "Permanently delete a trip.")
        @ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Trip deleted successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiResponse.class))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trip not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = SwaggerExamples.AdminTrip.TRIP_NOT_FOUND_RESPONSE)))
        })
        public ResponseEntity<ApiResponse<Void>> deleteTrip(@PathVariable Long id) {
                tripService.deleteTrip(id);
                return ResponseEntity.ok(ApiResponse.success(Messages.Success.TRIP_DELETED, null));
        }
}
