package com.workvenue.backend.repository;

import com.workvenue.backend.data.entity.Visiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VisiterRepository extends JpaRepository<Visiter, UUID> {

}
