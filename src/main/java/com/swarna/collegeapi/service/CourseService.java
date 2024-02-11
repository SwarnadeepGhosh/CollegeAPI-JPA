package com.swarna.collegeapi.service;

import com.swarna.collegeapi.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    public List<Course> findAllCourses();

    Course findCourseById(Long courseId);
}
