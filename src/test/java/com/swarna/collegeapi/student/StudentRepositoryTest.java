package com.swarna.collegeapi.student;


import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.swarna.collegeapi.guardian.Guardian;

@SpringBootTest // Used for testing. This will NOT flush the data when test completed
//@DataJpaTest - Used to test repository layer. This will test and flush the data when test completed
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository ;

//	@Test
//	public void saveStudent() {
//		Student student = Student.builder()
//				.emailId("test@gmail.com")
//				.firstName("Vito")
//				.lastName("COC")
////				.guardianName("SampleGuardian")
////				.guardianEmail("g@gmail.com")
////				.guardianMobile("9479474843")
//				.build();
//		studentRepository.save(student);
//	}
/*
	@Test
	public void saveStudentWithGuardian() {
		Guardian guardian = Guardian.builder()
				.name("AyanGuardian")
				.email("ayanGuardian@gmail.com")
				.mobile("93737362373")
				.build();
		
		Student student = Student.builder()
				.firstName("Ayan")
				.lastName("Desai")
				.emailId("ayan@gmail.com")
				.guardain(guardian)
				.build();
		studentRepository.save(student);
	}
	
	@Test
	public void printAllStudent() {
		List<Student> studentList = studentRepository.findAll();
		System.out.println("Students List : " +studentList);
	}
	
	@Test
	public void printStudentByFirstName() {
		List<Student> students = studentRepository.findByFirstName("Ayan");
		System.out.println("Student : " +students);
	}
	
	
//	@Test
//	public void printStudentBasedOnGuardianName() {
//		List<Student> students = studentRepository.findByGuardianName("AyanGuardian");
//		System.out.println("Student : " +students);
//	}
	
	@Test
	public void getStudentByEmailAddress() {
		Student student = studentRepository.getStudentByEmailAddress("ayan@gmail.com");
		System.out.println("Student : " +student);
	}
	
	@Test
	public void getStudentFirstNameByEmailAddress() {
		String firstName = studentRepository.getStudentFirstNameByEmailAddress("chayan@gmail.com");
		System.out.println("firstName : " +firstName);
	}
	*/
	@Test
	public void getStudentByEmailAddressNative() {
	    Student student = studentRepository.getStudentByEmailAddressNative("ayan@gmail.com");
	    System.out.println("Student : " +student);
	}
	
	@Test
	public void getStudentByEmailAddressNativeNamedParam() {
	    Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("ayan@gmail.com");
	    System.out.println("Student : " +student);
	}
	

	
}
