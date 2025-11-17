package com.bookingmx.reservations.model;

/**
 * Enumeration representing the possible states of a reservation.
 *
 * <p>This enum is used to determine whether a reservation is still valid
 * or has been canceled. Service-layer logic prevents updates to canceled
 * reservations, ensuring data integrity throughout the reservation lifecycle.</p>
 *
 * <ul>
 *   <li>{@link #ACTIVE} – The reservation is currently valid and can be updated.</li>
 *   <li>{@link #CANCELED} – The reservation has been canceled and cannot be modified.</li>
 * </ul>
 */
public enum ReservationStatus {
    /** The reservation is active and valid. */
    ACTIVE,

    /** The reservation has been canceled and cannot be updated. */
    CANCELED
}
