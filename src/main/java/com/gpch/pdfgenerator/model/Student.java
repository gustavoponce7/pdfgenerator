package com.gpch.pdfgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Integer id;
    private String name;
    private String lastName;
    private LocalDate birthday;
    private String nationality;
    private String university;
    private Boolean active;
}
