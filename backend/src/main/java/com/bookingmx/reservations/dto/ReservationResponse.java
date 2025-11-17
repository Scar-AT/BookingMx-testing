package com.bookingmx.reservations.dto;

import com.bookingmx.reservations.model.ReservationStatus;
import java.time.LocalDate;

/**
 * Data Transfer Object representing the response returned after creating,
 * updating, canceling, or fetching reservations.
 *
 * <p>This DTO exposes reservation information in a clean, controlled format
 * without exposing internal model details or unnecessary data.</p>
 *
 * <p>It is typically returned by the controller layer and consumed by clients
 * such as frontend applications or APIs.</p>
 */
public class ReservationResponse {

    /** Unique identifier of the reservation. */
    private Long id;

    /** Name of the guest who made the reservation. */
    private String guestName;

    /** Name of the hotel where the reservation was made. */
    private String hotelName;

    /** Date when the guest checks into the hotel. */
    private LocalDate checkIn;

    /** Date when the guest checks out of the hotel. */
    private LocalDate checkOut;

    /** Current status of the reservation (ACTIVE or CANCELED). */
    private ReservationStatus status;

    /**
     * Constructs a new response DTO containing reservation data.
     *
     * @param id        the reservation ID
     * @param guestName the name of the guest
     * @param hotelName the name of the hotel
     * @param checkIn   check-in date
     * @param checkOut  check-out date
     * @param status    current reservation status
     */
    public ReservationResponse(Long id, String guestName, String hotelName,
                               LocalDate checkIn, LocalDate checkOut, ReservationStatus status) {
        this.id = id;
        this.guestName = guestName;
        this.hotelName = hotelName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    /** @return the reservation ID */
    public Long getId() { return id; }

    /** @return the guest's name */
    public String getGuestName() { return guestName; }

    /** @return the hotel's name */
    public String getHotelName() { return hotelName; }

    /** @return the check-in date */
    public LocalDate getCheckIn() { return checkIn; }

    /** @return the check-out date */
    public LocalDate getCheckOut() { return checkOut; }

    /** @return the current reservation status */
    public ReservationStatus getStatus() { return status; }
}
