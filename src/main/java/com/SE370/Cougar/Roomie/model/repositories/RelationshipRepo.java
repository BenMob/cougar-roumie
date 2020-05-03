package com.SE370.Cougar.Roomie.model.repositories;

import com.SE370.Cougar.Roomie.model.entities.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipRepo extends JpaRepository<Relationship, Integer> {
}
