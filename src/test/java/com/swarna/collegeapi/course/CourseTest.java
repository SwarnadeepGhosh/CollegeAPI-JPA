package com.swarna.collegeapi.course;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Used for testing. This will NOT flush the data when test completed
class CourseTest {

	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void printAllCourses() {
		List<Course> courses = courseRepository.findAll();
		
		System.out.println("Courses = " + courses);
	}
}
