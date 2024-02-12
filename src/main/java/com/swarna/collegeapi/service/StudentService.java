package com.swarna.collegeapi.service;

import com.swarna.collegeapi.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> findAllStudents();

    Student findStudentById(Long studentId);

    Student addStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Long studentId);
}
