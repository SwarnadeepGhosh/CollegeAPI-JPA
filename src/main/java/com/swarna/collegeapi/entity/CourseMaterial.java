package com.swarna.collegeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swarna.collegeapi.utility.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course_material", schema = Constants.API_SCHEMA)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
//        @JsonBackReference
//    	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "courseMaterial")
//        @JsonIgnore
//    	private Course course;

    public CourseMaterial(CourseMaterial courseMaterial) {
        this.courseMaterialId = null;
        this.url = courseMaterial.getUrl();
    }
}
