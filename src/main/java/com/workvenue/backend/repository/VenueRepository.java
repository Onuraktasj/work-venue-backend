package com.workvenue.backend.repository;

import com.workvenue.backend.data.entity.Venue;
import com.workvenue.backend.data.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface VenueRepository extends JpaRepository<Venue, UUID> {

    Optional<Venue> getVenueByName(String name);
    @Query("SELECT i FROM Venue i")
    Set<Venue> getAllVenues();

    /*
    Get random venues in each query.
     */
    @Query(nativeQuery = true, value = "SELECT * from venues order by random()")
    Set<Venue> getRandomVenues();
}
