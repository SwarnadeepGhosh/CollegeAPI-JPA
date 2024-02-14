package com.swarna.collegeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swarna.collegeapi.utility.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "teacher", schema = Constants.API_SCHEMA)
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_seq",
            sequenceName = "teacher_seq",
            allocationSize = 1, schema = Constants.API_SCHEMA
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_seq"
    )
    private Long teacherId;
    private String firstName;
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    @JsonIgnore
    @ToString.Exclude
    private List<Course> courses;

    public Teacher(Teacher t) {
        this.teacherId = null;
        this.firstName = t.getFirstName();
        this.lastName = t.getLastName();
    }
}
