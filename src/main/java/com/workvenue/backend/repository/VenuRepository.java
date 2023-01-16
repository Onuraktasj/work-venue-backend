package com.workvenue.backend.repository;

import com.workvenue.backend.data.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VenuRepository extends JpaRepository<Venue, UUID> {

    Optional<Venue> getVenueByName(String name);
}
