package com.swarna.collegeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swarna.collegeapi.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
