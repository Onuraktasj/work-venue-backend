package com.workvenue.backend.repository;

import com.workvenue.backend.data.entity.Visiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VisiterRepository extends JpaRepository<Visiter, UUID> {
}
