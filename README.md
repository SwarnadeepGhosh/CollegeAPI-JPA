# README - CollegeAPI-JPA



### Introduction

In this project, I went through various important and powerful annotations and methods of Spring Data JPA. This project is a personal hands on example on Spring JPA Framework in Depth. This is a JPA based API project for college which demonstrates in depth practive of Spring Data JPA.



### Dependencies used in Spring Boot

- PostgreSQL Driver 
- Spring Web
- Lombok
- Spring Data JPA



### Database

#### Tables and Mappings

- 5 tables - tbl_student, tbl_course, student_course_map, teacher, course_material

- Course and CourseMaterial is One to One Relationship. For one course, there will be one Course Material and vice versa.
- One Teacher can taught many Courses  (OneToMany Relationship)
- Similarlarly Many courses can be taught by one teacher (ManyToOne Relationship)
- Many Student can opt for many courses available ((ManyToMany Relationship))



#### Database ER Diagram

![CollegeAPI-JPA-ER-Diagram](CollegeAPI-JPA-ER-Diagram1.png)



### Annotations used

```java
@SpringBootApplication
@SpringBootTest
@Repository
@Autowired
@Test

@Entity
@Data // Lombok will generate Getter and Setters with this annotation
@AllArgsConstructor //Lombok will generate @AllArgsConstructor
@NoArgsConstructor //Lombok will generate @NoArgsConstructor
@Builder //Used to test easily
@Table //Table name will be created with name tbl_student.
@Column

@Id // primary key
@SequenceGenerator //This will generate required generator
@GeneratedValue // This will assign Generated Value of student_sequence into studentId
@Embedded // Embedding guardain into here.
@Embeddable // Mentioning  Spring so that Guardian can be embeddable in Student class.
@AttributeOverrides({  // This will take "name" column and insert in table with "guardain_name" column name
	@AttributeOverride(
			name = "name",
			column = @Column(name = "guardain_name")
	)})

@OneToOne
@JoinColumn // foreign key
@ManyToOne
@ManyToMany
@JoinTable // used to create student_course_map for ManyToMany mapping

@Query // JPQL, Native SQL Query and Query with named Parameter
@Modifying // Make this method to modify value in database
@Transactional // Transaction is created -> operations insert/update is done -> transaction committed
@Param // Named Param - :<parameter_name>, which needs to declared by @Param("parameter_name") tag for each parameters
```

