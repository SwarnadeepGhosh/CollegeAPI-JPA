package com.swarna.collegeapi.student;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.swarna.collegeapi.guardian.Guardian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok will generate Getter and Setters with this annotation
@AllArgsConstructor //Lombok will generate @AllArgsConstructor
@NoArgsConstructor //Lombok will generate @NoArgsConstructor
@Builder
//Table name will be created with name tbl_student. This way we can rename tables and columns.
@Table(
		name="student",
		uniqueConstraints = @UniqueConstraint(name="emailid_unique" ,columnNames = "email_address")
) 
public class Student {
	
	//Now we want to generate Primary key studentId automatically by a Database Sequence. Lets create it.
	@Id
	@SequenceGenerator( //This will generate required generator
			name = "student_sequence",
			sequenceName = "",
			allocationSize = 1
	)
	@GeneratedValue( // This will assign Generated Value of student_sequence into studentId
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
	)
	private long studentId;
	
	private String firstName;
	private String lastName;
	
	@Column(name = "email_address") // renaming auto generated email_id to email_address
	private String emailId;
	
	@Embedded // Embedding guardain into here.
	private Guardian guardain;
	
	
}
