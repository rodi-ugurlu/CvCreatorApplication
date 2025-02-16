package com.rodiugurlu.cvcreator.repository;

import com.rodiugurlu.cvcreator.entity.WorkExperience;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
    List<WorkExperience> findByCv_Id(int cv_Id);
    @Modifying
    @Transactional
    @Query("DELETE FROM WorkExperience w WHERE w.cv.id = :cvId")
    void deleteByCvId(@Param("cvId") int cvId);
}
