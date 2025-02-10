package com.rodiugurlu.cvcreator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCv {
    private int id;
    private String position;
    private String photo;
    private String fullName;
    private String location;
    private String phone;
    private String github;
    private String linkedin;
    private String about;
    private String skill;
    private List<DtoProject> projects;
    private List<DtoEducation> educations;
    private List<DtoReference> references;
    private List<DtoWorkExperience> workExperiences;

}
