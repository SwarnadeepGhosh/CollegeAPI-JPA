package com.swarna.collegeapi.entity;

import javax.persistence.*;

import com.swarna.collegeapi.utility.Constants;
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
@Table(name="course_material", schema = Constants.API_SCHEMA)
public class CourseMaterial {

	@Id
	@SequenceGenerator(
			name = "course_material_seq",
			sequenceName = "course_material_seq",
			allocationSize = 1, schema = Constants.API_SCHEMA
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "course_material_seq"
	)
	private Long courseMaterialId;
	private String url;
	
	// courseMaterial cant exist without course and for one course, there will be one Course Material and vice versa.
//	@OneToOne(
//			cascade = CascadeType.ALL,
//			fetch = FetchType.LAZY,
//			optional = false
//	)
//	@JoinColumn( // foreign key
//			name = "course_id", // it will save with this name in course_material table.
//			referencedColumnName = "courseId" // courseId is coming from course.courseId
//	)
//	private Course course;

//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "courseMaterial")
//	private Course course;
	
}
