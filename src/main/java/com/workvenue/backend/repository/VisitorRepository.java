package com.workvenue.backend.repository;

import com.workvenue.backend.data.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, UUID> {

    @Query("SELECT i FROM Visitor i")
    Set<Visitor> getAllVisitors();
    Visitor getUserByEmail(String email);

}
