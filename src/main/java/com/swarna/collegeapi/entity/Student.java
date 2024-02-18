package com.swarna.collegeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swarna.collegeapi.utility.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data // Lombok will generate Getter and Setters with this annotation
@AllArgsConstructor //Lombok will generate @AllArgsConstructor
@NoArgsConstructor //Lombok will generate @NoArgsConstructor
@Builder //Used to test easily
//Table name will be created with name tbl_student. This way we can rename tables and columns.
@Table(
        name = "tbl_student", schema = Constants.API_SCHEMA,
        uniqueConstraints = @UniqueConstraint(name = "emailid_unique", columnNames = "email_address")
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {

    //Now we want to generate Primary key studentId automatically by a Database Sequence. Lets create it.
    @Id
    @SequenceGenerator( //This will generate required generator
            name = "student_seq",
            sequenceName = "student_seq",
            allocationSize = 1, schema = Constants.API_SCHEMA
    )
    @GeneratedValue( // This will assign Generated Value of student_seq into studentId
            strategy = GenerationType.SEQUENCE,
            generator = "student_seq"
    )
    private Long studentId;

    private String firstName;
    private String lastName;

    @Column(name = "email_address", // renaming auto generated email_id to email_address
            nullable = false // everytime we should be getting the value.
    )
    private String emailId;

    @Embedded // Embedding guardain into here.
    private Guardian guardain;

//	private String guardianName;
//	private String guardianEmail;
//	private String guardianMobile;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY) // This is the same attribute name used in User Class.
    @JsonIgnore
    @ToString.Exclude
    private List<Course> courses;

    public Student(Student student) {
        this.studentId = null;
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.emailId = student.getEmailId();
        this.guardain = student.getGuardain();
    }
}
