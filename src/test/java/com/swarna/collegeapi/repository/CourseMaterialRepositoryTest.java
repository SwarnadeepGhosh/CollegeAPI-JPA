package com.swarna.collegeapi.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swarna.collegeapi.entity.Course;
import com.swarna.collegeapi.entity.CourseMaterial;

@SpringBootTest // Used for testing. This will NOT flush the data when test completed
class CourseMaterialRepositoryTest {

	@Autowired
	private CourseMaterialRepository courseMaterialRepository;
	
	@Test
	public void saveCourseMaterial () {
		
		Course course = Course.builder()
				.title("JavaScript")
				.credit(4)
				.build();
		
		CourseMaterial courseMaterial = CourseMaterial.builder()
				.course(course)
				.url("https://www.javascript.com")
				.build();
		
		courseMaterialRepository.save(courseMaterial);
		
	}
	
	@Test
	public void printAllCourseMaterials() {
		List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
		
		System.out.println("Course Materials = " + courseMaterials);
	}
	
	

}
