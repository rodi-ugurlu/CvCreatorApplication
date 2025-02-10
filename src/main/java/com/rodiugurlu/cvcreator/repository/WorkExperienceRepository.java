package com.rodiugurlu.cvcreator.repository;

import com.rodiugurlu.cvcreator.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
    List<WorkExperience> findByCv_Id(int cv_Id);
}
