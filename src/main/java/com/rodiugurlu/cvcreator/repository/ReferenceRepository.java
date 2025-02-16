package com.rodiugurlu.cvcreator.repository;

import com.rodiugurlu.cvcreator.entity.Reference;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference, Integer> {
    List<Reference> findByCv_Id(int cvId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Reference r WHERE r.cv.id = :cvId")
    void deleteByCvId(@Param("cvId") int cvId);
}
