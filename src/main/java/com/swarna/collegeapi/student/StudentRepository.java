package com.swarna.collegeapi.student;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByFirstName(String firstName);
	List<Student> findByFirstNameContaining(String name);
	List<Student> findByLastNameNotNull();
//	public List<Student> findByGuardianName(String guardianName);
	
	//JPQL - based on Class name, not table name (eg- Student), ?1 = first argument
	// To select specific column value like firstName, we should use SELECT s.firstName ...
	@Query("SELECT s from Student s where s.emailId = ?1")
	Student getStudentByEmailAddress(String emailId);
	
	//JPQL -To select specific column value like firstName, we should use SELECT s.firstName ...
	@Query("SELECT s.firstName from Student s where s.emailId = ?1")
	String getStudentFirstNameByEmailAddress(String emailId);
	
	//Native Query - using original table name
	@Query(
	      value="SELECT * from tbl_student s where s.email_address = ?1",
	      nativeQuery = true
	)
	Student getStudentByEmailAddressNative(String emailId);
	
	//Named Param - :<parameter_name>, which needs to declared by @Param("parameter_name") tag for each parameters
	@Query(
	    value="SELECT * from tbl_student s where s.email_address = :emailId",
	    nativeQuery = true
	)
	Student getStudentByEmailAddressNativeNamedParam(
			@Param("emailId") String emailId
	);
	
	
	// UPDATE AND MODIFICATION ------------------------------
	
	@Modifying // Make this method to modify value in database
	@Transactional // Transaction is created -> operations insert/update is done -> transaction committed
	@Query(
		    value="UPDATE tbl_student set first_name = :firstName where email_address = :emailId ",
		    nativeQuery = true
	)
	int updateStudentNameByEmailId(
			@Param("firstName") String firstName,
			@Param("emailId") String emailId
	);
	

	
}
