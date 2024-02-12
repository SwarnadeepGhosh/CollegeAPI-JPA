package com.swarna.collegeapi.controller;

import com.swarna.collegeapi.entity.Student;
import com.swarna.collegeapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{studentId}")
    public Student findStudentById(@PathVariable("studentId") Long studentId) {
        return studentService.findStudentById(studentId);
    }

    @PostMapping("/")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}
