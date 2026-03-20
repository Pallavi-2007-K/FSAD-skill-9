package com.klu.skill9.controller;

import com.klu.skill9.model.Student;
import com.klu.skill9.exception.StudentNotFoundException;
import com.klu.skill9.exception.InvalidInputException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Map<Long, Student> students = new HashMap<>();

    static {
        students.put(1L, new Student(1L, "Tejaswi", "CSE-1"));
        students.put(2L, new Student(2L, "Vara", "HTE"));
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {

        Long studentId;

        try {
            studentId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Student ID must be a number");
        }

        Student student = students.get(studentId);

        if (student == null) {
            throw new StudentNotFoundException("Student with ID " + studentId + " not found");
        }

        return student;
    }
}