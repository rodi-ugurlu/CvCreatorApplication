package com.rodiugurlu.cvcreator.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.rodiugurlu.cvcreator.entity.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generateCvPdf(Cv cv) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            // PDF Yazarını oluştur
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);

            // PDF Belgesini oluştur
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // PDF Başlığı
            document.add(new Paragraph("Curriculum Vitae (CV)")
                    .setFontSize(18));

            document.add(new Paragraph("\n")); // Boşluk eklemek için

            // CV Genel Bilgileri
            document.add(new Paragraph("Full Name: " + cv.getFullName()));
            document.add(new Paragraph("Location: " + cv.getLocation()));
            document.add(new Paragraph("Phone: " + cv.getPhone()));
            document.add(new Paragraph("GitHub: " + cv.getGithub()));
            document.add(new Paragraph("LinkedIn: " + cv.getLinkedin()));
            document.add(new Paragraph("About: " + cv.getAbout()));
            document.add(new Paragraph("Skills: " + cv.getSkill()));
            document.add(new Paragraph("\n"));

            // Eğitim Bilgileri
            document.add(new Paragraph("Education"));
            Table educationTable = new Table(4); // 4 sütun: Üniversite, Departman, Başlangıç ve Bitiş Tarihi
            educationTable.addCell("University");
            educationTable.addCell("Department");
            educationTable.addCell("Start Date");
            educationTable.addCell("End Date");
            educationTable.setMarginLeft(3.4f);

            for (Education education : cv.getEducations()) {
                educationTable.addCell(education.getUniversity());
                educationTable.addCell(education.getDepartment());
                educationTable.addCell(education.getStartDate());
                educationTable.addCell(education.getEndDate());
            }
            document.add(educationTable);
            document.add(new Paragraph("\n"));

            // Projeler
            document.add(new Paragraph("Projects"));
            for (Project project : cv.getProjects()) {
                document.add(new Paragraph("Project Name: " + project.getName()));
                document.add(new Paragraph("Description: " + project.getDescription()));
                document.add(new Paragraph("Link: " + project.getLink()));
                document.add(new Paragraph("\n"));
            }

            // İş Deneyimleri
            document.add(new Paragraph("Work Experiences"));
            Table workTable = new Table(4); // 4 sütun: Şirket, Pozisyon, Yıl, Açıklama
            workTable.addCell("Company");
            workTable.addCell("Position");
            workTable.addCell("Year");
            workTable.addCell("Description");

            for (WorkExperience experience : cv.getWorkExperiences()) {
                workTable.addCell(experience.getCompany());
                workTable.addCell(experience.getPosition());
                workTable.addCell(experience.getYear());
                workTable.addCell(experience.getDescription());
            }
            document.add(workTable);
            document.add(new Paragraph("\n"));

            // Referanslar
            document.add(new Paragraph("References"));
            for (Reference reference : cv.getReferences()) {
                document.add(new Paragraph("Name: " + reference.getFullName()));
                document.add(new Paragraph("Position: " + reference.getPosition()));
                document.add(new Paragraph("Company: " + reference.getCompany()));
                document.add(new Paragraph("Phone: " + reference.getPhone()));
                document.add(new Paragraph("\n"));
            }

            // Belgeyi kapat
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }
}
