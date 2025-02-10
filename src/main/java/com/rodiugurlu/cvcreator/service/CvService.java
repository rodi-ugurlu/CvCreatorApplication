package com.rodiugurlu.cvcreator.service;

import com.rodiugurlu.cvcreator.dto.DtoCv;
import com.rodiugurlu.cvcreator.entity.Cv;

import java.util.List;

public interface CvService {
    Cv createCv(Cv cv);

    DtoCv updateCv(DtoCv updatedCv);

    void deleteCv(int id);

    DtoCv getCv(int cvId);

    List<DtoCv> getUserCvs(int userId);

}

