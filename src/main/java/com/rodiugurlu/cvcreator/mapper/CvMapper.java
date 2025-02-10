package com.rodiugurlu.cvcreator.mapper;

import com.rodiugurlu.cvcreator.dto.*;
import com.rodiugurlu.cvcreator.entity.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CvMapper {

    @Mapping(source = "projects", target = "projects")
    @Mapping(source = "educations", target = "educations")
    @Mapping(source = "references", target = "references")
    @Mapping(source = "workExperiences", target = "workExperiences")
    DtoCv   cvToDtoCv(Cv cv);

    CvMapper INSTANCE = Mappers.getMapper(CvMapper.class);

    void updateCvFromDto(DtoCv dtoCv, @MappingTarget Cv cv);

    List<DtoProject> projectsToDtoProjects(List<Project> projects);

    List<DtoEducation> educationsToDtoEducations(List<Education> educations);

    List<DtoReference> referencesToDtoReferences(List<Reference> references);

    List<DtoWorkExperience> workExperiencesToDtoWorkExperiences(List<WorkExperience> workExperiences);
}
