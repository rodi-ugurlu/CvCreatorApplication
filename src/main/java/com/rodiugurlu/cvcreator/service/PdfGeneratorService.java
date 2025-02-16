package com.rodiugurlu.cvcreator.service;

import com.rodiugurlu.cvcreator.entity.Cv;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
@AllArgsConstructor
public class PdfGeneratorService {

    private final TemplateEngine templateEngine;

    public ByteArrayInputStream generatePdf(Cv cv) {
        Context context = new Context();
        context.setVariable("cv", cv);
        String htmlContent = templateEngine.process("cv_template", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("PDF oluşturma hatası: " + e.getMessage(), e);
        }

    }
}