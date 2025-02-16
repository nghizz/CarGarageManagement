package com.example.api.repository;

import com.example.api.entity.SpareParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartsRepository extends JpaRepository<SpareParts, Long> {
}