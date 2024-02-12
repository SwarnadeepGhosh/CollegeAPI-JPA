package com.swarna.collegeapi.service.impl;

import com.swarna.collegeapi.entity.Student;
import com.swarna.collegeapi.repository.StudentRepository;
import com.swarna.collegeapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long studentId) {
        Optional<Student> optional = studentRepository.findById(studentId);
        return optional.orElse(null);
    }

    /**
     * @param student
     * @return
     */
    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * @param student
     * @return
     */
    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * @param studentId
     */
    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
