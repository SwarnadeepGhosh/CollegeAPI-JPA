# Spring Data JPA - Depth Tutorial



### Table of contents

- [Spring Data JPA - Depth Tutorial](#spring-data-jpa---depth-tutorial)
  - [Setup and Prerequisite](#setup-and-prerequisite)
    - [Student Entity](#student-entity)
      - [Different JPA Annotations](#different-jpa-annotations)
    - [Guardian Entity](#guardian-entity)
      - [Testing with Database entry](#testing-with-database-entry)



## Setup and Prerequisite

```properties
Project - Maven project
Language - Java
Spring Boot- 2.6.3

Project Metadata
Group - com.swarna
Artifact - CollegeAPI-JPA
Name - CollegeAPI-JPA
Description - A complete API for college which demonstrates in depth practive of Spring Data JPA
Package name - com.swarna.collegeapi

Packaging - war
Java - 8

Dependencies
- PostgreSQL Driver 
- Spring Web
- Lombok
- Spring Data JPA
```



I have initialised mySQL Database and connected Spring Project with it.

***application.properties***

```properties
#JPA config
#update means directly change in db while I run application
spring.jpa.hibernate.ddl-auto:update
spring.jpa.show-sql: true

#mySQL Database Configurations
#spring.datasource.url=jdbc:mysql://sql6.freesqldatabase.com:3306/sql6472211
#spring.datasource.username=sql6472211
#spring.datasource.password=BKZrwMePLL
#spring.datasource.driver-class-name=com.mysql.jdbc.Driver
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

# Connecting to Postgres Database
#spring.datasource.url=jdbc:postgresql://host:port/database
spring.datasource.url=jdbc:postgresql://free-tier12.aws-ap-south-1.cockroachlabs.cloud:26257/swarna-db-200.defaultdb
spring.datasource.username=swarnadeep
spring.datasource.password=<my_password>
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
```



Database ER Diagram

![JPA_inDepth-ER-DIagram](E:\My-Projects\CollegeAPI-JPA\JPA_inDepth-ER-DIagram.jpg)



### Student Entity

#### Different JPA Annotations

- @Entity - used to tell JPA to create a table, @Id - used to select primary key.
- @Table, @Column - used to rename table or column and to specify Unique constraints
- @Data, @AllArgsConstructor, @NoArgsConstructor, @Builder - these are Lombok annotations , which will create getters, setters and constructors in background.
- @SequenceGenerator - to generate sequence and @GeneratedValue - to assign sequence to a attribute

Here is ***Student.java*** for reference 

```java
package com.swarna.collegeapi.student;
...
@Entity //means table 
@Data // Lombok will generate Getter and Setters with this annotation
@AllArgsConstructor //Lombok will generate @AllArgsConstructor
@NoArgsConstructor //Lombok will generate @NoArgsConstructor
@Builder
//Table name will be created with name tbl_student. We can rename tables and columns.
@Table(
		name="tbl_student",
		uniqueConstraints = @UniqueConstraint(
                name="emailid_unique" ,
                columnNames = "email_address")
) 
public class Student {
	
	//Now we want to generate Primary key studentId automatically by a Database Sequence.
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
	
	@Column(name = "email_address", // renaming auto generated email_id to email_address
			nullable = false // everytime we should be getting the value.
	)
	private String emailId;
	
	private String guardianName;
	private String guardianEmail;
	private String guardianMobile;
}
```



Now we creating ***StudentRepository.java*** Interface. 

```java
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
//JpaRepository ultimately extends CrudRepository
```



#### Testing Student Entity

Right click on ***StudentRepository.java*** -> New -> JUnit Test Case -> ***StudentRepositoryTest.java***

```java
...
@SpringBootTest // Used for testing. This will NOT flush the data when test completed
//@DataJpaTest - Used to test repository layer. This will test and flush the data when test completed
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository ;

	@Test
	public void saveStudent() {
		Student student = Student.builder()
				.emailId("test@gmail.com")
				.firstName("Vito")
				.lastName("COC")
				.guardianName("SampleGuardian")
				.guardianEmail("g@gmail.com")
				.guardianMobile("9479474843")
				.build();
		studentRepository.save(student);
	}
	
	@Test
	public void printAllStudent() {
		List<Student> studentList = studentRepository.findAll();
		System.out.println("Students List : " +studentList);
	}	
}
```

So now right click on ***StudentRepositoryTest.java*** -> Run as Junit Test

Now we will make the student class better by group guardian details in the same student table only.





### Guardian Entity

We have initialized Guardian details like guardianName and others in Student.java but ideally it should be in a seperate class. So creating Guardian.java and changing Student.java

Guardian.java

```java
@Embeddable // Mentioning  Spring so that Guardian can be embeddable in Student class.
@Data
@AllArgsConstructor
@NoArgsConstructor 
@AttributeOverrides({  // This will take "name" column and insert in table with "guardain_name" column name
	@AttributeOverride(
			name = "name",
			column = @Column(name = "guardain_name")
	),
	@AttributeOverride(
			name = "email",
			column = @Column(name = "guardain_email")
	),
	@AttributeOverride(
			name = "mobile",
			column = @Column(name = "guardain_mobile")
	)
})
public class Guardian {

	private String name;
	private String email;
	private String mobile;
}
```

***Student.java***

```java
public class Student {
	...
	@Embedded // Embedding guardain into here.
	private Guardian guardain;
}
```



#### Testing with Database entry

Right click on ***StudentRepository.java*** -> New -> JUnit Test Case -> ***StudentRepositoryTest.java***

```java
...
@SpringBootTest // Used for testing. This will NOT flush the data when test completed
//@DataJpaTest - Used to test repository layer. This will test and flush the data when test completed
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository ;
...
    @Test
	public void saveStudentWithGuardian() {
		Guardian guardian = Guardian.builder()
				.name("ayanGuardian")
				.email("ayanGuardian@gmail.com")
				.mobile("9373736373")
				.build();
		
		Student student = Student.builder()
				.firstName("Ayan")
				.lastName("Desai")
				.emailId("ayan@gmail.com")
				.guardain(guardian)
				.build();
		studentRepository.save(student);
	}
}
```

So now right click on ***StudentRepositoryTest.java*** -> Run as Junit Test

Now we will make the student class better by group guardian details in the same student table only.



### JPA Repository methods and Queries

#### [All essential methods for JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)

***StudentRepository.java*** - some auto implemented methods example

```java
public List<Student> findByFirstName(String firstName);
public List<Student> findByFirstNameContaining(String name);
public List<Student> findByLastNameNotNull();
// No need to implement, just declaration. JPA will do implementation for us.
```

***StudentRepositoryTest.java***- a auto implemented method test

```java
@Test
public void printStudentByFirstName() {
    List<Student> students = studentRepository.findByFirstName("Ayan");
    System.out.println("Student with Name Ayan : " +students);
}
```





#### @Query - JPQL Query

JPQL is slightly different from normal SQL.

***StudentRepository.java*** - Getting a student data by email id

```java
//JPQL - based on Class name, not table name (eg- Student), ?1 = first argument
@Query("SELECT s from Student s where s.emailId = ?1")
Student getStudentByEmailAddress(String emailId);

// To select specific column value like firstName, we should use SELECT s.firstName
```

***StudentRepositoryTest.java*** - Getting a student data by email id

```java
@Test
public void getStudentByEmailAddress() {
    Student student = studentRepository.getStudentByEmailAddress("ayan@gmail.com");
    System.out.println("Student : " +student);
}
```



***StudentRepository.java*** - Getting only Firstname by filtering email id

```java
//JPQL -To select specific column value like firstName, we should use SELECT s.firstName 
@Query("SELECT s.firstName from Student s where s.emailId = ?1")
String getStudentFirstNameByEmailId(String emailId);
```

***StudentRepositoryTest.java***- Getting only Firstname by filtering email id

```java
@Test
public void getStudentFirstNameByEmailId() {
    String firstName= studentRepository.getStudentFirstNameByEmailId("chayan@gmail.com");
    System.out.println("firstName : " +firstName);
}
```



#### Native SQL @Query in JPA

JPA also supports native SQL queries , by which we can write same sql queries in jpa as what we do in any SQL language.

***StudentRepository.java*** - Getting a student data by email id

```java
//Native Query - using original table name, ?1 = first argument
@Query(
    value="SELECT * from tbl_student s where s.email_address = ?1",
    nativeQuery = true
)
Student getStudentByEmailAddressNative(String emailId);
```

***StudentRepositoryTest.java*** - Getting a student data by email id

```java
@Test
public void getStudentByEmailAddressNative() {
    Student student = studentRepository.getStudentByEmailAddressNative("ayan@gmail.com");
    System.out.println("Student : " +student);
}
```



#### Query Named @Param

Everytime `?1` is not the best approach. So we will now use named parameters.

***StudentRepository.java*** - Getting a student data by email id

```java
//Named Param - :<parameter_name>, which needs to declared by @Param("parameter_name") tag for each parameters
@Query(
    value="SELECT * from tbl_student s where s.email_address = :emailId",
    nativeQuery = true
)
Student getStudentByEmailAddressNativeNamedParam(
    @Param("emailId") String emailId
);
```

***StudentRepositoryTest.java*** - Getting a student data by email id

```java
@Test
public void getStudentByEmailAddressNativeNamedParam() {
    Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("ayan@gmail.com");
    System.out.println("Student : " +student);
}
```





#### Transactional and @Modifying Annotation

Used to Update / Delete the data.
