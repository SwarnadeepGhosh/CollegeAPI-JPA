package com.swarna.collegeapi.service.impl;

import com.swarna.collegeapi.entity.Course;
import com.swarna.collegeapi.repository.CourseRepository;
import com.swarna.collegeapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseById(Long courseId) {
        Optional<Course> optional = courseRepository.findById(courseId);
        return optional.orElse(null);
    }
}
