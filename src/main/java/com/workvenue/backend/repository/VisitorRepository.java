package com.workvenue.backend.repository;

import com.workvenue.backend.data.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, UUID> {
    Set<Visitor> findAllVisitors();

    Visitor findByEmail(String email);

    Visitor findByUserName(String userName);
}
