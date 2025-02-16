package com.rodiugurlu.cvcreator.repository;

import com.rodiugurlu.cvcreator.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CvRepository extends JpaRepository<Cv, Integer> {
    List<Cv> findByUser_Id(int userId);

    @Modifying
    @Query("DELETE FROM Cv c WHERE c.id = :id")
    void deleteCvById(@Param("id") int id);


    Cv getCvById(int cvId);

    List<Cv> id(int id);

}
