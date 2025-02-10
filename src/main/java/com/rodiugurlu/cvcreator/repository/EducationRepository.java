package com.rodiugurlu.cvcreator.repository;

import com.rodiugurlu.cvcreator.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Integer> {
    List<Education> findByCv_Id(int cv_Id);
}
