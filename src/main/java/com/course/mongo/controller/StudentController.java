package com.course.mongo.controller;

import com.course.mongo.entity.Student;
import com.course.mongo.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        val student1 = studentService.addStudent(student);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        val student1 = studentService.addStudent(student);
        return new ResponseEntity<>(student1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findStudent(@PathVariable String id) {
        val student = studentService.findStudent(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
