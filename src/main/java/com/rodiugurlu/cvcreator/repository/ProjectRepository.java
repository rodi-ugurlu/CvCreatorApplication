package com.rodiugurlu.cvcreator.repository;

import com.rodiugurlu.cvcreator.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByCv_Id(int cv_Id);
}
