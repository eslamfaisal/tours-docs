package com.egypttours.trip.service;

import com.egypttours.common.exception.ResourceNotFoundException;
import com.egypttours.trip.domain.Trip;
import com.egypttours.trip.domain.TripStatus;
import com.egypttours.trip.dto.request.CreateTripRequest;
import com.egypttours.trip.dto.request.UpdateTripRequest;
import com.egypttours.trip.dto.response.TripResponse;
import com.egypttours.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    public TripResponse createTrip(CreateTripRequest request) {
        Trip trip = Trip.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .destination(request.getDestination())
                .durationInDays(request.getDurationInDays())
                .price(request.getPrice())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .maxTravelers(request.getMaxTravelers())
                .status(TripStatus.DRAFT) // Default to DRAFT
                .build();

        Trip savedTrip = tripRepository.save(trip);
        return mapToResponse(savedTrip);
    }

    public TripResponse getTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", id));
        return mapToResponse(trip);
    }

    public List<TripResponse> getAllTrips() {
        return tripRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<TripResponse> getPublishedTrips() {
        return tripRepository.findByStatus(TripStatus.PUBLISHED).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TripResponse getPublishedTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .filter(t -> t.getStatus() == TripStatus.PUBLISHED)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", id)); // Or specific "not found"
        return mapToResponse(trip);
    }

    public TripResponse updateTrip(Long id, UpdateTripRequest request) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", id));

        trip.setTitle(request.getTitle());
        trip.setDescription(request.getDescription());
        trip.setDestination(request.getDestination());
        trip.setDurationInDays(request.getDurationInDays());
        trip.setPrice(request.getPrice());
        trip.setStartDate(request.getStartDate());
        trip.setEndDate(request.getEndDate());
        trip.setMaxTravelers(request.getMaxTravelers());
        trip.setStatus(request.getStatus());
        trip.setFeatured(request.getFeatured());

        Trip updatedTrip = tripRepository.save(trip);
        return mapToResponse(updatedTrip);
    }

    public void deleteTrip(Long id) {
        if (!tripRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trip", "id", id);
        }
        tripRepository.deleteById(id);
    }

    private TripResponse mapToResponse(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .title(trip.getTitle())
                .description(trip.getDescription())
                .destination(trip.getDestination())
                .durationInDays(trip.getDurationInDays())
                .price(trip.getPrice())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .maxTravelers(trip.getMaxTravelers())
                .status(trip.getStatus())
                .featured(trip.isFeatured())
                .build();
    }
}
