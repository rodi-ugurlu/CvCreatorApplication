package com.rodiugurlu.cvcreator.service;

import com.rodiugurlu.cvcreator.entity.Cv;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    private final TemplateEngine templateEngine;

    public PdfGeneratorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    public ByteArrayInputStream generatePdf(Cv cv) {
        Context context = new Context();
        context.setVariable("cv", cv);
        String htmlContent = templateEngine.process("cv_template", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();

            // PDF boyut ve margin ayarları
            renderer.setDocumentFromString(htmlContent);
//            renderer.getSharedContext().setMarginTop(20);
//            renderer.getSharedContext().setMarginBottom(20);
//            renderer.getSharedContext().setMarginLeft(20);
//            renderer.getSharedContext().setMarginRight(20);

            renderer.layout();
            renderer.createPDF(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("PDF oluşturma hatası: " + e.getMessage(), e);
        }

    }
}