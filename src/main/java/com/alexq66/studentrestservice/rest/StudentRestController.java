package com.alexq66.studentrestservice.rest;

import com.alexq66.studentrestservice.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        if(studentId >= myStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("Student ID not found - " + studentId);
        }

        return myStudents.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND)    ;
    }
}
