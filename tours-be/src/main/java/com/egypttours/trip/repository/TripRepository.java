package com.egypttours.trip.repository;

import com.egypttours.trip.domain.Trip;
import com.egypttours.trip.domain.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByStatus(TripStatus status);

    List<Trip> findByFeaturedTrueAndStatus(TripStatus status);
}
