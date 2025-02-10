package com.rodiugurlu.cvcreator.repository;

import com.rodiugurlu.cvcreator.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference, Integer> {
    List<Reference> findByCv_Id(int cvId);
}
