package com.bookingmx.reservations.service;

import com.bookingmx.reservations.dto.ReservationRequest;
import com.bookingmx.reservations.model.Reservation;
import com.bookingmx.reservations.model.ReservationStatus;
import com.bookingmx.reservations.repo.ReservationRepository;
import com.bookingmx.reservations.exception.BadRequestException;
import com.bookingmx.reservations.exception.NotFoundException;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class that manages the business logic for hotel reservations.
 *
 * <p>This class acts as the main interaction layer between controllers and the
 * underlying repository. It provides operations for creating, updating,
 * canceling, and listing reservations while enforcing validation rules.</p>
 *
 * <p>Key responsibilities:
 * <ul>
 *   <li>Validate reservation dates</li>
 *   <li>Prevent updates to canceled reservations</li>
 *   <li>Ensure that only future reservations are allowed</li>
 *   <li>Provide CRUD-like interaction through the repository</li>
 * </ul>
 * </p>
 */
@Service
public class ReservationService {

    /** Internal repository for storing and retrieving reservations. */
    private final ReservationRepository repo = new ReservationRepository();

    /**
     * Retrieves a list of all current reservations.
     *
     * @return a list containing all reservations in the repository
     */
    public List<Reservation> list() {
        return repo.findAll();
    }

    /**
     * Creates a new reservation after validating the incoming data.
     *
     * <p>The reservation will only be saved if:
     * <ul>
     *   <li>Both dates are provided</li>
     *   <li>The check-out date is after the check-in date</li>
     *   <li>The dates are not in the past</li>
     * </ul>
     * </p>
     *
     * @param req the reservation details provided by the client
     * @return the stored {@link Reservation} instance
     * @throws BadRequestException if the dates are invalid or incomplete
     */
    public Reservation create(ReservationRequest req) {
        validateDates(req.getCheckIn(), req.getCheckOut());
        Reservation r = new Reservation(
            null,
            req.getGuestName(),
            req.getHotelName(),
            req.getCheckIn(),
            req.getCheckOut()
        );
        return repo.save(r);
    }

    /**
     * Updates an existing reservation.
     *
     * <p>The update will only succeed if:
     * <ul>
     *   <li>The reservation exists</li>
     *   <li>The reservation is still active (not canceled)</li>
     *   <li>New dates pass validation checks</li>
     * </ul>
     * </p>
     *
     * @param id the ID of the reservation to update
     * @param req the updated reservation data
     * @return the updated and saved {@link Reservation} instance
     * @throws NotFoundException if the reservation does not exist
     * @throws BadRequestException if the reservation is canceled or dates are invalid
     */
    public Reservation update(Long id, ReservationRequest req) {
        Reservation existing = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Reservation not found"));

        if (!existing.isActive()) {
            throw new BadRequestException("Cannot update a canceled reservation");
        }

        validateDates(req.getCheckIn(), req.getCheckOut());

        existing.setGuestName(req.getGuestName());
        existing.setHotelName(req.getHotelName());
        existing.setCheckIn(req.getCheckIn());
        existing.setCheckOut(req.getCheckOut());

        return repo.save(existing);
    }

    /**
     * Cancels an active reservation, marking its status as {@link ReservationStatus#CANCELED}.
     *
     * @param id the ID of the reservation to cancel
     * @return the updated reservation with status set to CANCELED
     * @throws NotFoundException if no reservation exists with the given ID
     */
    public Reservation cancel(Long id) {
        Reservation existing = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Reservation not found"));

        existing.setStatus(ReservationStatus.CANCELED);
        return repo.save(existing);
    }

    /**
     * Validates that reservation dates follow all business rules.
     *
     * <p>Rules enforced:
     * <ul>
     *   <li>Dates cannot be null</li>
     *   <li>Check-out must be after check-in</li>
     *   <li>Neither date may be in the past</li>
     * </ul>
     * </p>
     *
     * @param in the check-in date
     * @param out the check-out date
     * @throws BadRequestException if any validation rule is violated
     */
    private void validateDates(LocalDate in, LocalDate out) {
        if (in == null || out == null) {
            throw new BadRequestException("Dates cannot be null");
        }
        if (!out.isAfter(in)) {
            throw new BadRequestException("Check-out must be after check-in");
        }
        if (in.isBefore(LocalDate.now())) {
            throw new BadRequestException("Check-in must be in the future");
        }
        if (out.isBefore(LocalDate.now())) {
            throw new BadRequestException("Check-out must be in the future");
        }
    }
}
