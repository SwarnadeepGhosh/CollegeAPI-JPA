package com.swarna.collegeapi.entity;

import com.swarna.collegeapi.utility.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course", schema = Constants.API_SCHEMA)
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

    @OneToOne(
            cascade = CascadeType.PERSIST
//			optional = false
    )
    @JoinColumn( // foreign key
            name = "course_material_id", // it will save with this name in course_material table.
            referencedColumnName = "courseMaterialId"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.PERSIST)
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

}
