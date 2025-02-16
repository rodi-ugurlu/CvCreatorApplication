package com.rodiugurlu.cvcreator.mapper;

import com.rodiugurlu.cvcreator.dto.DtoCv;
import com.rodiugurlu.cvcreator.dto.DtoEducation;
import com.rodiugurlu.cvcreator.dto.DtoProject;
import com.rodiugurlu.cvcreator.dto.DtoReference;
import com.rodiugurlu.cvcreator.dto.DtoWorkExperience;
import com.rodiugurlu.cvcreator.entity.Cv;
import com.rodiugurlu.cvcreator.entity.Education;
import com.rodiugurlu.cvcreator.entity.Project;
import com.rodiugurlu.cvcreator.entity.Reference;
import com.rodiugurlu.cvcreator.entity.WorkExperience;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T16:14:42+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class CvMapperImpl implements CvMapper {

    @Override
    public DtoCv cvToDtoCv(Cv cv) {
        if ( cv == null ) {
            return null;
        }

        DtoCv dtoCv = new DtoCv();

        dtoCv.setProjects( projectsToDtoProjects( cv.getProjects() ) );
        dtoCv.setEducations( educationsToDtoEducations( cv.getEducations() ) );
        dtoCv.setReferences( referencesToDtoReferences( cv.getReferences() ) );
        dtoCv.setWorkExperiences( workExperiencesToDtoWorkExperiences( cv.getWorkExperiences() ) );
        dtoCv.setId( cv.getId() );
        dtoCv.setPosition( cv.getPosition() );
        dtoCv.setPhoto( cv.getPhoto() );
        dtoCv.setFullName( cv.getFullName() );
        dtoCv.setLocation( cv.getLocation() );
        dtoCv.setPhone( cv.getPhone() );
        dtoCv.setGithub( cv.getGithub() );
        dtoCv.setLinkedin( cv.getLinkedin() );
        dtoCv.setAbout( cv.getAbout() );
        dtoCv.setSkill( cv.getSkill() );

        return dtoCv;
    }

    @Override
    public void updateCvFromDto(DtoCv dtoCv, Cv cv) {
        if ( dtoCv == null ) {
            return;
        }

        cv.setId( dtoCv.getId() );
        cv.setPhoto( dtoCv.getPhoto() );
        cv.setFullName( dtoCv.getFullName() );
        cv.setPosition( dtoCv.getPosition() );
        cv.setLocation( dtoCv.getLocation() );
        cv.setPhone( dtoCv.getPhone() );
        cv.setGithub( dtoCv.getGithub() );
        cv.setLinkedin( dtoCv.getLinkedin() );
        cv.setAbout( dtoCv.getAbout() );
        cv.setSkill( dtoCv.getSkill() );
        if ( cv.getProjects() != null ) {
            List<Project> list = dtoProjectListToProjectList( dtoCv.getProjects() );
            if ( list != null ) {
                cv.getProjects().clear();
                cv.getProjects().addAll( list );
            }
            else {
                cv.setProjects( null );
            }
        }
        else {
            List<Project> list = dtoProjectListToProjectList( dtoCv.getProjects() );
            if ( list != null ) {
                cv.setProjects( list );
            }
        }
        if ( cv.getWorkExperiences() != null ) {
            List<WorkExperience> list1 = dtoWorkExperienceListToWorkExperienceList( dtoCv.getWorkExperiences() );
            if ( list1 != null ) {
                cv.getWorkExperiences().clear();
                cv.getWorkExperiences().addAll( list1 );
            }
            else {
                cv.setWorkExperiences( null );
            }
        }
        else {
            List<WorkExperience> list1 = dtoWorkExperienceListToWorkExperienceList( dtoCv.getWorkExperiences() );
            if ( list1 != null ) {
                cv.setWorkExperiences( list1 );
            }
        }
        if ( cv.getReferences() != null ) {
            List<Reference> list2 = dtoReferenceListToReferenceList( dtoCv.getReferences() );
            if ( list2 != null ) {
                cv.getReferences().clear();
                cv.getReferences().addAll( list2 );
            }
            else {
                cv.setReferences( null );
            }
        }
        else {
            List<Reference> list2 = dtoReferenceListToReferenceList( dtoCv.getReferences() );
            if ( list2 != null ) {
                cv.setReferences( list2 );
            }
        }
        if ( cv.getEducations() != null ) {
            List<Education> list3 = dtoEducationListToEducationList( dtoCv.getEducations() );
            if ( list3 != null ) {
                cv.getEducations().clear();
                cv.getEducations().addAll( list3 );
            }
            else {
                cv.setEducations( null );
            }
        }
        else {
            List<Education> list3 = dtoEducationListToEducationList( dtoCv.getEducations() );
            if ( list3 != null ) {
                cv.setEducations( list3 );
            }
        }
    }

    @Override
    public List<DtoProject> projectsToDtoProjects(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<DtoProject> list = new ArrayList<DtoProject>( projects.size() );
        for ( Project project : projects ) {
            list.add( projectToDtoProject( project ) );
        }

        return list;
    }

    @Override
    public List<DtoEducation> educationsToDtoEducations(List<Education> educations) {
        if ( educations == null ) {
            return null;
        }

        List<DtoEducation> list = new ArrayList<DtoEducation>( educations.size() );
        for ( Education education : educations ) {
            list.add( educationToDtoEducation( education ) );
        }

        return list;
    }

    @Override
    public List<DtoReference> referencesToDtoReferences(List<Reference> references) {
        if ( references == null ) {
            return null;
        }

        List<DtoReference> list = new ArrayList<DtoReference>( references.size() );
        for ( Reference reference : references ) {
            list.add( referenceToDtoReference( reference ) );
        }

        return list;
    }

    @Override
    public List<DtoWorkExperience> workExperiencesToDtoWorkExperiences(List<WorkExperience> workExperiences) {
        if ( workExperiences == null ) {
            return null;
        }

        List<DtoWorkExperience> list = new ArrayList<DtoWorkExperience>( workExperiences.size() );
        for ( WorkExperience workExperience : workExperiences ) {
            list.add( workExperienceToDtoWorkExperience( workExperience ) );
        }

        return list;
    }

    protected Project dtoProjectToProject(DtoProject dtoProject) {
        if ( dtoProject == null ) {
            return null;
        }

        Project project = new Project();

        project.setName( dtoProject.getName() );
        project.setDescription( dtoProject.getDescription() );
        project.setLink( dtoProject.getLink() );

        return project;
    }

    protected List<Project> dtoProjectListToProjectList(List<DtoProject> list) {
        if ( list == null ) {
            return null;
        }

        List<Project> list1 = new ArrayList<Project>( list.size() );
        for ( DtoProject dtoProject : list ) {
            list1.add( dtoProjectToProject( dtoProject ) );
        }

        return list1;
    }

    protected WorkExperience dtoWorkExperienceToWorkExperience(DtoWorkExperience dtoWorkExperience) {
        if ( dtoWorkExperience == null ) {
            return null;
        }

        WorkExperience workExperience = new WorkExperience();

        workExperience.setPosition( dtoWorkExperience.getPosition() );
        workExperience.setYear( dtoWorkExperience.getYear() );
        workExperience.setCompany( dtoWorkExperience.getCompany() );
        workExperience.setDescription( dtoWorkExperience.getDescription() );

        return workExperience;
    }

    protected List<WorkExperience> dtoWorkExperienceListToWorkExperienceList(List<DtoWorkExperience> list) {
        if ( list == null ) {
            return null;
        }

        List<WorkExperience> list1 = new ArrayList<WorkExperience>( list.size() );
        for ( DtoWorkExperience dtoWorkExperience : list ) {
            list1.add( dtoWorkExperienceToWorkExperience( dtoWorkExperience ) );
        }

        return list1;
    }

    protected Reference dtoReferenceToReference(DtoReference dtoReference) {
        if ( dtoReference == null ) {
            return null;
        }

        Reference reference = new Reference();

        reference.setFullName( dtoReference.getFullName() );
        reference.setPosition( dtoReference.getPosition() );
        reference.setCompany( dtoReference.getCompany() );
        reference.setPhone( dtoReference.getPhone() );

        return reference;
    }

    protected List<Reference> dtoReferenceListToReferenceList(List<DtoReference> list) {
        if ( list == null ) {
            return null;
        }

        List<Reference> list1 = new ArrayList<Reference>( list.size() );
        for ( DtoReference dtoReference : list ) {
            list1.add( dtoReferenceToReference( dtoReference ) );
        }

        return list1;
    }

    protected Education dtoEducationToEducation(DtoEducation dtoEducation) {
        if ( dtoEducation == null ) {
            return null;
        }

        Education education = new Education();

        education.setUniversity( dtoEducation.getUniversity() );
        education.setDepartment( dtoEducation.getDepartment() );
        education.setStartDate( dtoEducation.getStartDate() );
        education.setEndDate( dtoEducation.getEndDate() );

        return education;
    }

    protected List<Education> dtoEducationListToEducationList(List<DtoEducation> list) {
        if ( list == null ) {
            return null;
        }

        List<Education> list1 = new ArrayList<Education>( list.size() );
        for ( DtoEducation dtoEducation : list ) {
            list1.add( dtoEducationToEducation( dtoEducation ) );
        }

        return list1;
    }

    protected DtoProject projectToDtoProject(Project project) {
        if ( project == null ) {
            return null;
        }

        DtoProject dtoProject = new DtoProject();

        dtoProject.setName( project.getName() );
        dtoProject.setDescription( project.getDescription() );
        dtoProject.setLink( project.getLink() );

        return dtoProject;
    }

    protected DtoEducation educationToDtoEducation(Education education) {
        if ( education == null ) {
            return null;
        }

        DtoEducation dtoEducation = new DtoEducation();

        dtoEducation.setUniversity( education.getUniversity() );
        dtoEducation.setDepartment( education.getDepartment() );
        dtoEducation.setStartDate( education.getStartDate() );
        dtoEducation.setEndDate( education.getEndDate() );

        return dtoEducation;
    }

    protected DtoReference referenceToDtoReference(Reference reference) {
        if ( reference == null ) {
            return null;
        }

        DtoReference dtoReference = new DtoReference();

        dtoReference.setFullName( reference.getFullName() );
        dtoReference.setPosition( reference.getPosition() );
        dtoReference.setCompany( reference.getCompany() );
        dtoReference.setPhone( reference.getPhone() );

        return dtoReference;
    }

    protected DtoWorkExperience workExperienceToDtoWorkExperience(WorkExperience workExperience) {
        if ( workExperience == null ) {
            return null;
        }

        DtoWorkExperience dtoWorkExperience = new DtoWorkExperience();

        dtoWorkExperience.setPosition( workExperience.getPosition() );
        dtoWorkExperience.setYear( workExperience.getYear() );
        dtoWorkExperience.setCompany( workExperience.getCompany() );
        dtoWorkExperience.setDescription( workExperience.getDescription() );

        return dtoWorkExperience;
    }
}
