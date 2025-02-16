package com.rodiugurlu.cvcreator.manager;

import com.rodiugurlu.cvcreator.dto.*;
import com.rodiugurlu.cvcreator.entity.*;
import com.rodiugurlu.cvcreator.mapper.CvMapper;
import com.rodiugurlu.cvcreator.repository.*;
import com.rodiugurlu.cvcreator.service.CvService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CvManager implements CvService {
    private CvRepository cvRepository;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private EducationRepository educationRepository;
    private WorkExperienceRepository workExperienceRepository;
    private ReferenceRepository referenceRepository;
    private CvMapper cvMapper;
    private EntityManager entityManager;

    public String getCurrentUsername() {
        // SecurityContextHolder üzerinden oturum açmış kullanıcıyı al
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString(); // Eğer UserDetails değilse, principal'ı string olarak döndür
        }
    }


    @Override
    public Cv createCv(Cv cv) {
        if (cv.getPhoto() != null) {
            // Fotoğrafı base64'e çevirin ve saklayın
//            cv.setPhoto(Base64.getEncoder().encodeToString(cv.getPhoto().getBytes()));
            cv.setPhoto(cv.getPhoto());
        }
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + username));
        cv.setUser(user);
        cv.getProjects().forEach(project -> project.setCv(cv));
        cv.getWorkExperiences().forEach(workExperience -> workExperience.setCv(cv));
        cv.getReferences().forEach(reference -> reference.setCv(cv));
        cv.getEducations().forEach(education -> education.setCv(cv));
        cv.setUser(user);
        return cvRepository.save(cv);
    }

    @Override
    public DtoCv updateCv(DtoCv updatedDtoCv) {
        Optional<Cv> optional = cvRepository.findById(updatedDtoCv.getId());
        if (optional.isPresent()) {
            Cv existingCv = optional.get();

            // MapStruct ile DTO'dan mevcut entity'ye güncelle
            CvMapper.INSTANCE.updateCvFromDto(updatedDtoCv, existingCv);

            // Tüm ilişkili koleksiyonları güncelle
            existingCv.getReferences().forEach(ref -> ref.setCv(existingCv));
            existingCv.getEducations().forEach(edu -> edu.setCv(existingCv));
            existingCv.getWorkExperiences().forEach(exp -> exp.setCv(existingCv));
            existingCv.getProjects().forEach(proj -> proj.setCv(existingCv));

            cvRepository.save(existingCv);

            // DTO olarak geri döndür
            return CvMapper.INSTANCE.cvToDtoCv(existingCv);
        }
        return null;
    }


    @Override
    @Transactional
    public void deleteCv(int id) {
        Cv cv = cvRepository.findById(id).orElseThrow();
        educationRepository.deleteByCvId(cv.getId());
        workExperienceRepository.deleteByCvId(cv.getId());
        projectRepository.deleteByCvId(cv.getId());
        referenceRepository.deleteByCvId(cv.getId());
        cvRepository.deleteCvById(cv.getId());

    }


    @Override
    public DtoCv getCv(int cvId) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        String cvPhoto = cv.getPhoto();
        List<Project> projectList = projectRepository.findByCv_Id(cv.getId());
        List<Education> educationList = educationRepository.findByCv_Id(cv.getId());
        List<Reference> referenceList = referenceRepository.findByCv_Id(cv.getId());
        List<WorkExperience> workExperienceList = workExperienceRepository.findByCv_Id(cv.getId());

        DtoCv dtoCv = cvMapper.cvToDtoCv(cv);
        dtoCv.setProjects(cvMapper.projectsToDtoProjects(projectList));
        dtoCv.setEducations(cvMapper.educationsToDtoEducations(educationList));
        dtoCv.setReferences(cvMapper.referencesToDtoReferences(referenceList));
        dtoCv.setWorkExperiences(cvMapper.workExperiencesToDtoWorkExperiences(workExperienceList));

        return dtoCv;
    }

    public List<DtoCv> getUserCvs(int userId) {
        List<Cv> userCvList = cvRepository.findByUser_Id(userId);
        List<DtoCv> dtoCvList = new ArrayList<>();
        for (Cv cv : userCvList) {
            dtoCvList.add(cvMapper.cvToDtoCv(cv));
        }
        return dtoCvList;
    }
}