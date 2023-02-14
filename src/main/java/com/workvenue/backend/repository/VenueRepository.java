package com.workvenue.backend.repository;

import com.workvenue.backend.data.entity.Venue;
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

    List<Venue> getAllVenues(); //BERKIN TODO: sadece active statü olanları dönecek şekilde yaz.
    @Query(nativeQuery = true, value = "SELECT * from venues order by random()")
    Set<Venue> getRandomVenues();
}
