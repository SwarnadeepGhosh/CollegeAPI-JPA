# Spring Data JPA - Depth - CollegeAPI



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





#### Transactional and @Modifying - UPDATE

Used to Update / Insert / Delete the data.

***StudentRepository.java*** - Updating Firstname by email id

```java
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
```

***StudentRepositoryTest.java*** - Updating Firstname by email id

```java
@Test
public void updateStudentNameByEmailId() {
    studentRepository.updateStudentNameByEmailId("Updated Ayan","ayan@gmail.com");
}
```





### Relationships in JPA

#### @OneToOne Relationship

![JPA_inDepth-ER-DIagram](E:\My-Projects\CollegeAPI-JPA\JPA_inDepth-ER-DIagram.jpg)

- Course and CourseMaterial is One to One Relationship.
- For one course, there will be one Course Material and vice versa.
- First Created Course and CourseMaterial entity.

***Course.java***

```java
...
    @Table( name="tbl_course" ) 
    public class Course {
        @Id
        @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
        )
        @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
        )
        private Long courseId;
        private String title;
        private Integer credit;	
    }
```

***CourseMaterial.java***

```java
...
public class CourseMaterial {
    @Id
    @SequenceGenerator(
        name = "course_material_sequence",
        sequenceName = "course_material_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;
	
  @OneToOne // courseMaterial cant exist without course and for one course, there will be one Course Material and vice versa.
  
  @JoinColumn( // foreign key
	name = "course_id", //It will save as course_material table in DB with this name
	referencedColumnName = "courseId" // This is the Column name of course table
  )
	private Course course;	
}
```



Created two Interface CourseRepository.java and CourseMaterialRepository.java

```java
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{ }

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long>{}
```

Now we will testing CourseMaterialRepositoryTest.java

```java
...
@SpringBootTest // Used for testing. This will NOT flush the data when test completed
class CourseMaterialRepositoryTest {
	@Autowired
	private CourseMaterialRepository courseMaterialRepository;
	
    @Test
	public void saveCourseMaterial () {
		
		Course course = Course.builder()
				.credit(6)
				.title("Java Course Title")
				.build();
		
		CourseMaterial courseMaterial = CourseMaterial.builder()
				.course(course)
				.url("https://www.google.com/")
				.build();
		
		courseMaterialRepository.save(courseMaterial);
	}
}
/*
On running => it will throw error as below
Error => Object references an unsaved transient instance
Reason => we are trying to create CourseMaterial object , but referenced course is not in database.
Solution => CASCADING
```



#### Cascading

***CourseMaterial.java***

```java
@OneToOne( cascade = CascadeType.ALL) // Cascading 
@JoinColumn( name = "course_id", referencedColumnName = "courseId" )
private Course course;
```

This will cascade and save course first in database and then save course material, so that it will not throw error.



#### Fetch Types- Eager, Lazy

A JPA association can be fetched lazily or eagerly. The fetching strategy is controlled via the `fetch` attribute of the [`@OneToMany`](https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/), [`@OneToOne`](https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/), [`@ManyToOne`](https://vladmihalcea.com/manytoone-jpa-hibernate/), or [`@ManyToMany`](https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/). Types to fetch data from DB

- Eager Fetching
- Lazy Fetching

If I fetch CourseMaterial, do I need to fetch Course also, if yes, then I should use **Eager Fetching**, else in **Lazy Fetching**, it will not fetch Course , until I say so .



Here I am demonstrating LAZY Fetching.

***CourseMaterial.java***

```java
@OneToOne( 
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
) 
@JoinColumn( name = "course_id", referencedColumnName = "courseId" )
private Course course;
```

***CourseMaterialRepositoryTest.java***

Printing all course materials.

```java
@Test
public void printAllCourseMaterials() {
    List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();

    System.out.println("Course Materials = " + courseMaterials);
}
```



But it is Throwing error 

```
org.hibernate.LazyInitializationException: could not initialize proxy [com.swarna.collegeapi.course.Course#1]
```

In CourseMaterial.java, there is a toString() method which is calling for course, I have to remove that for now. 

***CourseMaterial.java***

```java
...
@ToString(exclude = "course") // excluding course toString method
public class CourseMaterial {
```

Now our test will successful.

Test Output :

```
Courses = [Course(courseId=1, title=Java Course Title, credit=6)]
```





#### Uni and Bi-directional Relationship

We have created **unidirectional** mapping from CourseMaterial using JOIN, and we fetched Course from CourseMaterial.

Now we will create **bidirectional** mapping, so that we can fetch courseMaterial from Course also.

***Course.java***

```java
public class Course {
	...
	@OneToOne(mappedBy = "course")
	private CourseMaterial courseMaterial;
}
```



***CourseRepositoryTest.java***

```java
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
```

Output : 

```
Courses = [Course(courseId=1, title=Java Course Title, credit=6, courseMaterial=CourseMaterial(courseMaterialId=1, url=https://www.google.com))]
```





#### @OneToMany Relationship

- One Teacher can taught many Courses  (OneToMany Relationship)
- Many courses can be taught by one teacher (ManyToOne Relationship)

Created ***Teacher.java***

```java
...
public class Teacher {

	@Id
	@SequenceGenerator(
			name = "teacher_sequence",
			sequenceName = "teacher_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "teacher_sequence"
	)
	private Long teacherId;
	private String firstName;
	private String lastName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    // this will create a foreign key in course table with name teacher_id
	private List<Course> courses;
}
```

Created ***TeacherRepository.java***

```java
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{ }
```

Created ***TeacherRepositoryTest.java***

Here we are adding Teacher after adding one course. But we have not added CourseMaterial for the new course. Now this is allowed. But we will disallow this in future.

```java
@SpringBootTest
class TeacherRepositoryTest {
	
	@Autowired
	private TeacherRepository teacherRepository;

	@Testd
	public void saveTeacher() {
		Course courseDba = Course.builder().title("DBA").credit(4).build();
		Course courseJava = Course.builder().title("JAVA").credit(5).build();
		
		Teacher teacher = Teacher.builder()
				.firstName("Ricky")
				.lastName("Ghosh")
				.courses(List.of(courseDba,courseJava))
				.build();
		
		teacherRepository.save(teacher);
	}
}
```



Now we are adding **optional=false** in CourseMaterial, so that a new CourseMaterial should be added for a new course.

***CourseMaterial.java***

```java
@OneToOne( 
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    optional = false // This will disallow new Course addition without new Course Material
) 
@JoinColumn( name = "course_id", referencedColumnName = "courseId" )
private Course course;
```



So if we try to save a new course material without course , it will throw error.





#### @ManyToOne Relationship

- One Teacher can taught many Courses  (OneToMany Relationship)
- Many courses can be taught by one teacher (ManyToOne Relationship)
- We should use @ManyToOne rather than @OneToMany whenever possible. JPA also prefers @ManyToOne

So first we are commenting @OnetToMany from Teacher entity.

***Teacher.java***

```java
/*@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
private List<Course> courses;*/
```

***Course.java***

```java
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
private Teacher teacher;
```

***CourseRepositoryTest.java***

```java
...
    @Test
    public void saveCourseWithTeacher() {
    Teacher teacher = Teacher.builder()
        .firstName("Amit")
        .lastName("Tiwari")
        .build();

    Course course = Course.builder()
        .title("Python")
        .credit(5)
        .teacher(teacher)
        .build();

    courseRepository.save(course);
	}	
```





#### Paging and Sorting

