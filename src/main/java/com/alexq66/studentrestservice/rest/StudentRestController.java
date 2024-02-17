package com.alexq66.studentrestservice.rest;

import com.alexq66.studentrestservice.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenneth Quinn on 2/17/2024
 */

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> myStudents;

    @PostConstruct
    public void loadMyStudents() {

        myStudents = new ArrayList<>();

        myStudents.add(new Student("Poornima", "Partel"));
        myStudents.add(new Student("Mario", "Rossi"));
        myStudents.add(new Student("Marry", "Smith"));
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return myStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        return myStudents.get(studentId);
    }
}
