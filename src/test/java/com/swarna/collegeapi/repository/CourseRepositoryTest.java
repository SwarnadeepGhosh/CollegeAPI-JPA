package com.swarna.collegeapi.repository;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swarna.collegeapi.entity.Course;
import com.swarna.collegeapi.entity.Teacher;
import com.swarna.collegeapi.repository.CourseRepository;

@SpringBootTest // Used for testing. This will NOT flush the data when test completed
class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void printAllCourses() {
		List<Course> courses = courseRepository.findAll();
		
		System.out.println("Courses = " + courses);
	}
	
	@Test
	public void saveCourseWithTeacher() {
		Teacher teacher = Teacher.builder()
				.firstName("Amit")
				.lastName("Tiwari")
				.build();
		
		Course course = Course.builder()
				.title("Python")
				.credit(5)
				.teacher(teacher)
				.build();
		
		courseRepository.save(course);
	}
	
}
