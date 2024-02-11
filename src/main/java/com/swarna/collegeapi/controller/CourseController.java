package com.swarna.collegeapi.controller;

import com.swarna.collegeapi.entity.Course;
import com.swarna.collegeapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/")
    public List<Course> findAllCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping("/{courseId}")
    public Course findCourseById(@PathVariable("courseId") Long courseId){
        return courseService.findCourseById(courseId);
    }

    @PostMapping("/")
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @PutMapping("/")
    public Course updateCourse(@RequestBody Course course){
        return courseService.updateCourse(course);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
    }
}
