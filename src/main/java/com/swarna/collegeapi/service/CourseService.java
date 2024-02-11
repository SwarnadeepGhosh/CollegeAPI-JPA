package com.swarna.collegeapi.service;

import com.swarna.collegeapi.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<Course> findAllCourses();

    Course findCourseById(Long courseId);

    Course addCourse(Course course);

    Course updateCourse(Course course);

    void deleteCourse(Long courseId);
}
