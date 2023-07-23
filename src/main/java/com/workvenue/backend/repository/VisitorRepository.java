package com.workvenue.backend.repository;

import com.workvenue.backend.data.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, UUID> {

    Visitor findByEmail(String email);
    Optional<Visitor> findByUsername(String username);
}