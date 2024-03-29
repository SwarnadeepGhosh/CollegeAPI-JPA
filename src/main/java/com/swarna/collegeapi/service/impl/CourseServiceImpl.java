package com.swarna.collegeapi.service.impl;

import com.swarna.collegeapi.entity.Course;
import com.swarna.collegeapi.entity.CourseMaterial;
import com.swarna.collegeapi.entity.Student;
import com.swarna.collegeapi.entity.Teacher;
import com.swarna.collegeapi.repository.CourseMaterialRepository;
import com.swarna.collegeapi.repository.CourseRepository;
import com.swarna.collegeapi.repository.StudentRepository;
import com.swarna.collegeapi.repository.TeacherRepository;
import com.swarna.collegeapi.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseMaterialRepository courseMaterialRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseById(Long courseId) {
        Optional<Course> optional = courseRepository.findById(courseId);
        return optional.orElse(null);
    }

    /**
     * @param course
     * @return
     */
//    @Override
//    public Course addCourse(Course course) {
//        log.info("Going to save CourseEntity= {}",course);
//        return courseRepository.save(course);
//    }

    @Override
    @Transactional
    public Course addCourse(Course course) {
        Teacher teacherEntity;
        CourseMaterial courseMaterialEntity;
        if (Objects.isNull(course.getCourseMaterial().getCourseMaterialId())) {
            courseMaterialEntity = new CourseMaterial(course.getCourseMaterial());
        } else {
            courseMaterialEntity = courseMaterialRepository
                    .findById(course.getCourseMaterial().getCourseMaterialId())
                    .orElse(new CourseMaterial(course.getCourseMaterial()));
        }
        log.info("courseMaterialEntity fetched = {}", courseMaterialEntity);

        if (Objects.isNull(course.getTeacher().getTeacherId())) {
            teacherEntity = new Teacher(course.getTeacher());
        } else {
            teacherEntity = teacherRepository
                    .findById(course.getTeacher().getTeacherId())
                    .orElse(new Teacher(course.getTeacher()));
        }
        log.info("teacherEntity fetched = {}", teacherEntity);

        List<Student> students = new ArrayList<>();
        course.getStudents().forEach(i -> {
            if(Objects.isNull(i.getStudentId())){
                students.add(new Student(i));
                log.info("StudentId is null or Student not found in DB");
            } else{
                Student studentEntity = studentRepository
                        .findById(i.getStudentId())
                        .orElse(new Student(i));
                students.add(studentEntity);
            }
        });
        log.info("students fetched = {}", students);

        Course courseEntity = Course.builder()
                .courseId(!Objects.isNull(course.getCourseId()) ? course.getCourseId() : null)
                .title(course.getTitle())
                .credit(course.getCredit())
                .courseMaterial(courseMaterialEntity)
                .teacher(teacherEntity)
                .students(students)
                .build();
        log.info("Going to save CourseEntity= {}", courseEntity);
        return courseRepository.save(courseEntity);
    }

    /**
     * @param course
     * @return
     */
    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * @param courseId
     */
    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
