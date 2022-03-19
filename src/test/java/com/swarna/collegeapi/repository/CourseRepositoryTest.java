package com.swarna.collegeapi.repository;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.swarna.collegeapi.entity.Course;
import com.swarna.collegeapi.entity.Guardian;
import com.swarna.collegeapi.entity.Student;
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
	
//	@Test
//	public void saveCourseWithTeacher() {
//		Teacher teacher = Teacher.builder()
//				.firstName("Amit")
//				.lastName("Tiwari")
//				.build();
//		
//		Course course = Course.builder()
//				.title("Python")
//				.credit(5)
//				.teacher(teacher)
//				.build();
//		
//		courseRepository.save(course);
//	}
	
	@Test
	public void findAllPagination() {
		Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
		Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);
		
		List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();
		Long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
		int totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();
		
		System.out.println("totalElements = " + totalElements);
		System.out.println("totalPages = " + totalPages);
		System.out.println("Courses = " + courses);
	}
	
	@Test
	public void findAllSorting() {
		Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
		Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());
		Pageable sortByTitleAndCredit = PageRequest.of(0, 2, Sort.by("title")
				.descending().and(Sort.by("credit")));
		
		List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
		System.out.println("Courses = " + courses);
	}
	
	/*
	@Test
	public void saveCourseWithStudentAndTeacher() {
		Teacher teacher = Teacher.builder()
				.firstName("Vartul")
				.lastName("Prasad")
				.build();
		
		Guardian guardian = Guardian.builder()
				.name("Prakash")
				.email("prakash@gmail.com")
				.mobile("123121211")
				.build();
		
		Student student = Student.builder()
				.firstName("Akash")
				.lastName("Bhowmik")
				.emailId("akash@gmail.com")
				.guardain(guardian)
				.build();
		
		Course course = Course.builder()
				.title("DSA")
				.credit(7)
				.teacher(teacher)
				.build();
		
		course.addStudents(student);
		
		courseRepository.save(course);
	}
	*/
}
