package com.swarna.collegeapi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
		name="tbl_course"
) 
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
	
	//  creating bidirectional mapping, so that we can fetch courseMaterial from Course also.
	@OneToOne(mappedBy = "course") // course is coming from courseMaterial.course
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
			)
	)
	private List<Student> students;
	
	public void addStudents(Student student) {
		if(students == null) students = new ArrayList<>();
		
		students.add(student);
	}
}
