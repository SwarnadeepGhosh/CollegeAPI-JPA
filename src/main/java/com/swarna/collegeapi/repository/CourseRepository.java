package com.swarna.collegeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swarna.collegeapi.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
