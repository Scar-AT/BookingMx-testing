package com.bookingmx.reservations.repo;

import com.bookingmx.reservations.model.Reservation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repository class that simulates persistent storage for reservations.
 * 
 * <p>This implementation uses an in-memory {@link ConcurrentHashMap} as a storage mechanism,
 * making it suitable for testing environments and lightweight applications where
 * a real database is not required.</p>
 *
 * <p>The repository is responsible for:
 * <ul>
 *   <li>Storing reservation instances</li>
 *   <li>Assigning incremental IDs to new reservations</li>
 *   <li>Providing CRUD-like operations on stored reservations</li>
 * </ul>
 * </p>
 *
 * <p><strong>Note:</strong> This repository is thread-safe thanks to 
 * {@link ConcurrentHashMap} and {@link AtomicLong}, although the overall application
 * is not designed for heavy concurrent load.</p>
 */
public class ReservationRepository {

    /** Internal storage of reservations mapped by their unique ID. */
    private final Map<Long, Reservation> store = new ConcurrentHashMap<>();

    /** Sequence generator used to assign unique incremental IDs to new reservations. */
    private final AtomicLong seq = new AtomicLong(1L);

    /**
     * Retrieves all stored reservations.
     *
     * @return a new {@link List} containing all reservations currently in storage
     */
    public List<Reservation> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * Attempts to find a reservation by its ID.
     *
     * @param id the unique identifier of the reservation
     * @return an {@link Optional} containing the reservation if found,
     *         or an empty Optional if it does not exist
     */
    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * Saves a reservation to storage.
     *
     * <p>If the reservation does not yet have an ID (i.e., {@code id == null}),
     * the repository assigns a new sequential ID before saving.</p>
     *
     * @param r the reservation to save
     * @return the saved reservation instance, including its assigned ID if it was newly created
     */
    public Reservation save(Reservation r) {
        if (r.getId() == null) {
            r.setId(seq.getAndIncrement());
        }
        store.put(r.getId(), r);
        return r;
    }

    /**
     * Deletes a reservation from storage by its ID.
     *
     * @param id the unique identifier of the reservation to remove
     */
    public void delete(Long id) {
        store.remove(id);
    }
}
