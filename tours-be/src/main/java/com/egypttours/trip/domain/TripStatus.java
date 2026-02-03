package com.egypttours.trip.domain;

public enum TripStatus {
    DRAFT, // Only visible to admins
    PUBLISHED, // Visible to all users
    ARCHIVED // No longer bookable
}
