package com.swarna.collegeapi.entity;

import java.util.List;

import javax.persistence.*;

import com.swarna.collegeapi.utility.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="course", schema = Constants.API_SCHEMA)
public class Course {

	@Id
	@SequenceGenerator(
			name = "course_seq",
			sequenceName = "course_seq",
			allocationSize = 1, schema = Constants.API_SCHEMA
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "course_seq"
	)
	private Long courseId;
	private String title;
	private Integer credit;
	
	//  creating bidirectional mapping, so that we can fetch courseMaterial from Course also.
//	@OneToOne(mappedBy = "course") // course is coming from courseMaterial.course
//	private CourseMaterial courseMaterial;

	@OneToOne(
			cascade = CascadeType.ALL,
//			fetch = FetchType.LAZY,
			optional = false
	)
	@JoinColumn( // foreign key
			name = "course_material_id", // it will save with this name in course_material table.
			referencedColumnName = "courseMaterialId" // courseId is coming from course.courseId
	)
	private CourseMaterial courseMaterial;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
	private Teacher teacher;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "student_course_map",
			joinColumns = @JoinColumn( // for students, what courses they have
					name = "course_id",
					referencedColumnName = "courseId"
			),
			inverseJoinColumns = @JoinColumn( // for courses, what should be the students
					name = "student_id",
					referencedColumnName = "studentId"
			), schema = Constants.API_SCHEMA
	)
	private List<Student> students;
	
//	public void addStudents(Student student) {
//		if(students == null) students = new ArrayList<>();
//
//		students.add(student);
//	}
}
