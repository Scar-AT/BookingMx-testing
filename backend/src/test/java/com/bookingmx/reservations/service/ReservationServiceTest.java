package com.bookingmx.reservations.service;

import com.bookingmx.reservations.dto.ReservationRequest;
import com.bookingmx.reservations.exception.BadRequestException;
import com.bookingmx.reservations.exception.NotFoundException;
import com.bookingmx.reservations.model.Reservation;
import com.bookingmx.reservations.model.ReservationStatus;
import com.bookingmx.reservations.repo.ReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    private ReservationRepository repo;   // real in-memory repo
    private ReservationService service;   // SUT

    @BeforeEach
    void setUp() throws Exception {
        repo = new ReservationRepository();
        service = new ReservationService();

        // inject real repo into private field
        Field f = ReservationService.class.getDeclaredField("repo");
        f.setAccessible(true);
        f.set(service, repo);
    }

    // Create reservations

    @Test
    void create_validReservation_returnsSavedReservation() {
        ReservationRequest req = new ReservationRequest();
        req.setGuestName("Scarlett");
        req.setHotelName("Hotel Azul");
        req.setCheckIn(LocalDate.now().plusDays(1));
        req.setCheckOut(LocalDate.now().plusDays(3));

        Reservation result = service.create(req);

        assertNotNull(result.getId());
        assertEquals("Scarlett", result.getGuestName());
        assertEquals(1, repo.findAll().size()); // stored in repo
    }

    @Test
    void create_invalidDates_throwsBadRequest() {
        ReservationRequest req = new ReservationRequest();
        req.setGuestName("Scarlett");
        req.setHotelName("Hotel Azul");
        req.setCheckIn(LocalDate.now().plusDays(5));
        req.setCheckOut(LocalDate.now().plusDays(1)); // invalid

        assertThrows(BadRequestException.class, () -> service.create(req));
    }


    // UPDATESS

    @Test
    void update_existingActiveReservation_updatesSuccessfully() {
        // store initial reservation
        Reservation existing = new Reservation(
                null, "Old Name", "Old Hotel",
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(4)
        );
        repo.save(existing);

        // update request
        ReservationRequest req = new ReservationRequest();
        req.setGuestName("Scarlett");
        req.setHotelName("Hotel Azul");
        req.setCheckIn(LocalDate.now().plusDays(3));
        req.setCheckOut(LocalDate.now().plusDays(6));

        Reservation updated = service.update(existing.getId(), req);

        assertEquals("Scarlett", updated.getGuestName());
        assertEquals("Hotel Azul", updated.getHotelName());
        assertEquals(LocalDate.now().plusDays(3), updated.getCheckIn());
    }

    @Test
    void update_nonExistingReservation_throwsNotFound() {
        ReservationRequest req = new ReservationRequest();
        req.setGuestName("X");
        req.setHotelName("Y");
        req.setCheckIn(LocalDate.now().plusDays(1));
        req.setCheckOut(LocalDate.now().plusDays(2));

        assertThrows(NotFoundException.class, () -> service.update(999L, req));
    }

    @Test
    void update_canceledReservation_throwsBadRequest() {
        Reservation r = new Reservation(
                null, "G", "H",
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(4)
        );
        r.setStatus(ReservationStatus.CANCELED);
        repo.save(r);

        ReservationRequest req = new ReservationRequest();
        req.setGuestName("New");
        req.setHotelName("New Hotel");
        req.setCheckIn(LocalDate.now().plusDays(3));
        req.setCheckOut(LocalDate.now().plusDays(5));

        assertThrows(BadRequestException.class, () -> service.update(r.getId(), req));
    }


    // CANCELLATIONS

    @Test
    void cancel_existingReservation_setsStatusToCanceled() {
        Reservation r = new Reservation(
                null, "A", "B",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3)
        );
        repo.save(r);

        Reservation result = service.cancel(r.getId());

        assertEquals(ReservationStatus.CANCELED, result.getStatus());
    }

    @Test
    void cancel_nonExistingReservation_throwsNotFound() {
        assertThrows(NotFoundException.class, () -> service.cancel(555L));
    }


    //  DATE VALIDATION

    @Test
    void create_nullDates_throwsBadRequest() {
        ReservationRequest req = new ReservationRequest();
        req.setGuestName("Scarlett");
        req.setHotelName("Hotel Azul");
        req.setCheckIn(null);
        req.setCheckOut(LocalDate.now().plusDays(3));

        assertThrows(BadRequestException.class, () -> service.create(req));
    }

    @Test
    void create_pastCheckIn_throwsBadRequest() {
        ReservationRequest req = new ReservationRequest();
        req.setGuestName("Scarlett");
        req.setHotelName("Hotel Azul");
        req.setCheckIn(LocalDate.now().minusDays(1)); // past date
        req.setCheckOut(LocalDate.now().plusDays(5));

        assertThrows(BadRequestException.class, () -> service.create(req));
    }
}
