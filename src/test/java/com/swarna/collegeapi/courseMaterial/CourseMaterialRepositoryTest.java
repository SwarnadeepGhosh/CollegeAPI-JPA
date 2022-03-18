package com.swarna.collegeapi.courseMaterial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swarna.collegeapi.course.Course;

@SpringBootTest // Used for testing. This will NOT flush the data when test completed
class CourseMaterialRepositoryTest {

	@Autowired
	private CourseMaterialRepository courseMaterialRepository;
	
	@Test
	public void saveCourseMaterial () {
		
		Course course = Course.builder()
				.title("Java Course Title")
				.credit(6)
				.build();
		
		CourseMaterial courseMaterial = CourseMaterial.builder()
				.course(course)
				.url("https://www.google.com")
				.build();
		
		courseMaterialRepository.save(courseMaterial);
	}
	
	

}
