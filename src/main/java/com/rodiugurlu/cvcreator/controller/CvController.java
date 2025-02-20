package com.rodiugurlu.cvcreator.controller;

import com.rodiugurlu.cvcreator.dto.DtoCv;
import com.rodiugurlu.cvcreator.entity.Cv;
import com.rodiugurlu.cvcreator.entity.User;
import com.rodiugurlu.cvcreator.repository.CvRepository;
import com.rodiugurlu.cvcreator.repository.UserRepository;
import com.rodiugurlu.cvcreator.service.CvService;
import com.rodiugurlu.cvcreator.service.PdfGeneratorService;
import com.rodiugurlu.cvcreator.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CvController {
    private final UserRepository userRepository;
    private final CvRepository cvRepository;
    private final CvService cvService;
    private final PdfService pdfService;
    private final PdfGeneratorService pdfGeneratorService;


    @PostMapping("/createcv")
    public ResponseEntity<Cv> createCv(@RequestBody Cv cv) {
        cvService.createCv(cv);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/updatecv")
    public ResponseEntity<DtoCv> updateCv(@RequestBody DtoCv dtoCv) {
        cvService.updateCv(dtoCv);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deletecv/{id}")
    public ResponseEntity<Void> deleteCv(@PathVariable int id) {
        cvService.deleteCv(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getcv/{cvId}")
    public ResponseEntity<DtoCv> getCv(@PathVariable int cvId) {
        DtoCv dtoCv = cvService.getCv(cvId);
        return ResponseEntity.ok(dtoCv);
    }

    @GetMapping("/downloadcv/{id}")
    public ResponseEntity<byte[]> downloadCvPdf(@PathVariable int id) {
        Cv cv = cvRepository.findById(id).orElseThrow(() -> new RuntimeException("CV cant found"));

        byte[] pdfBytes = pdfGeneratorService.generatePdf(cv).readAllBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=cv_" + id + ".pdf");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(pdfBytes);
    }

    @GetMapping("/getcvlist") // Path variable kaldırıldı!
    public ResponseEntity<List<DtoCv>> getCvList() {

        // Oturumdaki kullanıcıyı al
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // User'ı veritabanından çek (Örnek kod, kendi servisinize göre düzenleyin)
        Optional<User> user = userRepository.findByUsername(username);
        // User'ın CV'lerini getir
        List<DtoCv> cvList = cvService.getUserCvs(user.get().getId());

        return ResponseEntity.ok(cvList);
    }

    @GetMapping("/getuserinfo")
    @ResponseBody
    public Map<String, String> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        Map<String, String> response = new HashMap<>();
        response.put("username", userDetails.getUsername());
        return response;
    }

}
