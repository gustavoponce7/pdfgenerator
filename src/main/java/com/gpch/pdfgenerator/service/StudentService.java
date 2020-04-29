package com.gpch.pdfgenerator.service;

import com.gpch.pdfgenerator.model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StudentService {

    public List<Student> getStudents(){
        final List<Student> students = IntStream.range(1, 10)
                .mapToObj(v -> Student.builder()
                        .id(v)
                        .name("Name " + v)
                        .lastName("Last Name " + v)
                        .active(v%2==0?true:false)
                        .birthday(LocalDate.now())
                        .nationality("Nationality " + v)
                        .university("University " + v)
                        .build())
                .collect(Collectors.toList());
        return students;
    }
}
