package com.bookingmx.reservations.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Data Transfer Object used for creating or updating reservations.
 *
 * <p>This DTO is consumed by controller endpoints and validated automatically
 * using Jakarta Bean Validation annotations.</p>
 *
 * <p>Validation rules enforced:
 * <ul>
 *   <li>{@code guestName} must not be blank</li>
 *   <li>{@code hotelName} must not be blank</li>
 *   <li>{@code checkIn} must be a non-null future date</li>
 *   <li>{@code checkOut} must be a non-null future date</li>
 * </ul>
 * These validations ensure that only well-formed reservation requests reach the service layer.</p>
 */
public class ReservationRequest {

    /** Name of the guest creating the reservation. Must not be blank. */
    @NotBlank
    private String guestName;

    /** Name of the hotel where the reservation will take place. Must not be blank. */
    @NotBlank
    private String hotelName;

    /** Date on which the guest checks in. Must be in the future. */
    @NotNull @Future
    private LocalDate checkIn;

    /** Date on which the guest checks out. Must be in the future. */
    @NotNull @Future
    private LocalDate checkOut;

    /** @return the name of the guest */
    public String getGuestName() { return guestName; }

    /** @param guestName sets the name of the guest */
    public void setGuestName(String guestName) { this.guestName = guestName; }

    /** @return the name of the hotel */
    public String getHotelName() { return hotelName; }

    /** @param hotelName sets the name of the hotel */
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    /** @return the check-in date */
    public LocalDate getCheckIn() { return checkIn; }

    /** @param checkIn sets the check-in date */
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }

    /** @return the check-out date */
    public LocalDate getCheckOut() { return checkOut; }

    /** @param checkOut sets the check-out date */
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
}
