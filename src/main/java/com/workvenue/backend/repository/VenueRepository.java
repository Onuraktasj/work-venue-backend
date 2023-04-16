package com.workvenue.backend.repository;

import com.workvenue.backend.data.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VenueRepository extends JpaRepository<Venue, UUID> {

    Optional<Venue> getVenueByName(String name);

//    @Query(nativeQuery = true, value = "SELECT * from venues order by random()")
//    Set<Venue> getRandomVenues();
}