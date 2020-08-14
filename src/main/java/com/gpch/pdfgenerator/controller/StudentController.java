package com.gpch.pdfgenerator.controller;

import com.gpch.pdfgenerator.service.PdfService;
import com.gpch.pdfgenerator.service.StudentService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final PdfService pdfService;

    @Autowired
    public StudentController(StudentService studentService, PdfService pdfService) {
        this.studentService = studentService;
        this.pdfService = pdfService;
    }

    @GetMapping("/students")
    public ModelAndView studentsView(ModelAndView modelAndView) {
        modelAndView.addObject("students", studentService.getStudents());
        modelAndView.setViewName("students");
        return modelAndView;
    }

    @GetMapping("/download-pdf")
    public void downloadPDFResource(HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generatePdf().getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
