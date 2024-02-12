package com.swarna.collegeapi.service.impl;

import com.swarna.collegeapi.entity.Teacher;
import com.swarna.collegeapi.repository.TeacherRepository;
import com.swarna.collegeapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findTeacherById(Long teacherId) {
        Optional<Teacher> optional = teacherRepository.findById(teacherId);
        return optional.orElse(null);
    }

    /**
     * @param teacher
     * @return
     */
    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    /**
     * @param teacher
     * @return
     */
    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    /**
     * @param teacherId
     */
    @Override
    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
