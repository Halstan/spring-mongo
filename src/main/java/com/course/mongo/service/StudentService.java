package com.course.mongo.service;

import com.course.mongo.entity.Student;
import com.course.mongo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(String id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
