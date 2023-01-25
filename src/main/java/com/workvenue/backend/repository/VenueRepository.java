package com.workvenue.backend.repository;

import com.workvenue.backend.data.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface VenueRepository extends JpaRepository<Venue, UUID> {

    Optional<Venue> getVenueByName(String name);
    @Query("SELECT i FROM Venue i")
    Set<Venue> getAllVenues();

    @Query(nativeQuery = true, value = "SELECT * from venues order by random()")
    Set<Venue> getRandomVenues();
}
