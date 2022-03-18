package com.swarna.collegeapi.courseMaterial;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.swarna.collegeapi.course.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
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
	
	// courseMaterial cant exist without course and for one course, there will be one Course Material and vice versa.
	@OneToOne( 
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	) 
	@JoinColumn( // foreign key
			name = "course_id", // it will save with this name in course_material table.
			referencedColumnName = "courseId" // This is the Column name of course table
	)
	private Course course;
	
}
