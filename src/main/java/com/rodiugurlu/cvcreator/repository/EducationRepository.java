package com.rodiugurlu.cvcreator.repository;

import com.rodiugurlu.cvcreator.entity.Education;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Integer> {
    List<Education> findByCv_Id(int cv_Id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Education e WHERE e.cv.id = :cvId")
    void deleteByCvId(@Param("cvId") int cvId);
}