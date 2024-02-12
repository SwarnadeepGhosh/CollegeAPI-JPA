package com.swarna.collegeapi.service;

import com.swarna.collegeapi.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    List<Teacher> findAllTeachers();

    Teacher findTeacherById(Long teacherId);

    Teacher addTeacher(Teacher teacher);

    Teacher updateTeacher(Teacher teacher);

    void deleteTeacher(Long teacherId);
}
