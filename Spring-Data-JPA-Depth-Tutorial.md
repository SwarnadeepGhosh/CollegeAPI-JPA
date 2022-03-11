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
	
	@Column(name = "email_address") // renaming auto generated email_id to email_address
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

