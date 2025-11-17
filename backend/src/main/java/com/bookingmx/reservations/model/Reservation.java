package com.bookingmx.reservations.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a hotel reservation made by a guest.
 *
 * <p>This class serves as the core data model for the reservation system.
 * It encapsulates identifying information, guest details, hotel name,
 * reservation dates, and its current status.</p>
 *
 * <p>Instances of this class are stored and managed by the {@link com.bookingmx.reservations.repo.ReservationRepository}
 * and validated through business rules enforced in the service layer.</p>
 */
public class Reservation {

    /** Unique identifier for the reservation. Assigned by the repository. */
    private Long id;

    /** Name of the guest making the reservation. */
    private String guestName;

    /** Name of the hotel where the reservation is made. */
    private String hotelName;

    /** Date on which the guest checks into the hotel. */
    private LocalDate checkIn;

    /** Date on which the guest checks out of the hotel. */
    private LocalDate checkOut;

    /** Current status of the reservation (ACTIVE or CANCELED). */
    private ReservationStatus status = ReservationStatus.ACTIVE;

    /** Default constructor required for serialization and frameworks. */
    public Reservation() {}

    /**
     * Constructs a new reservation instance.
     *
     * @param id        the reservation ID, or {@code null} if it will be assigned later
     * @param guestName the name of the guest
     * @param hotelName the name of the hotel
     * @param checkIn   the check-in date
     * @param checkOut  the check-out date
     */
    public Reservation(Long id, String guestName, String hotelName,
                       LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.guestName = guestName;
        this.hotelName = hotelName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = ReservationStatus.ACTIVE;
    }

    /** @return the reservation ID */
    public Long getId() { return id; }

    /** @param id sets the reservation ID */
    public void setId(Long id) { this.id = id; }

    /** @return the guest name */
    public String getGuestName() { return guestName; }

    /** @param guestName sets the guest name */
    public void setGuestName(String guestName) { this.guestName = guestName; }

    /** @return the hotel name */
    public String getHotelName() { return hotelName; }

    /** @param hotelName sets the hotel name */
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    /** @return the check-in date */
    public LocalDate getCheckIn() { return checkIn; }

    /** @param checkIn sets the check-in date */
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }

    /** @return the check-out date */
    public LocalDate getCheckOut() { return checkOut; }

    /** @param checkOut sets the check-out date */
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }

    /** @return the current reservation status */
    public ReservationStatus getStatus() { return status; }

    /** @param status sets the reservation status */
    public void setStatus(ReservationStatus status) { this.status = status; }

    /**
     * Indicates whether the reservation is still active.
     *
     * @return {@code true} if the reservation is active, {@code false} otherwise
     */
    public boolean isActive() {
        return this.status == ReservationStatus.ACTIVE;
    }

    /**
     * Determines equality based on the reservation ID.
     *
     * @param o the object to compare
     * @return {@code true} if the objects share the same ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Generates a hash code based on the reservation ID.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
