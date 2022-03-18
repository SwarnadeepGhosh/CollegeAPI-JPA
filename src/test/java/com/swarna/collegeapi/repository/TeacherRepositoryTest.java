package com.swarna.collegeapi.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swarna.collegeapi.entity.Course;
import com.swarna.collegeapi.entity.Teacher;

@SpringBootTest
class TeacherRepositoryTest {
	
	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	public void saveTeacher() {
		Course courseDba = Course.builder()
				.title("DBA")
				.credit(4)
				.build();
		
		Course courseJava = Course.builder()
				.title("JAVA")
				.credit(5)
				.build();
		
		Teacher teacher = Teacher.builder()
				.firstName("Ricky")
				.lastName("Ghosh")
//				.courses(List.of(courseDba,courseJava))
				.build();
		
		teacherRepository.save(teacher);
	}

}
