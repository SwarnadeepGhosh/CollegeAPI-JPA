package com.swarna.collegeapi.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable // Mentioning  Spring so that Guardian can be embeddable in Student class.
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Builder
@AttributeOverrides({  // This will take "name" column and insert in table with "guardian_name" column name
	@AttributeOverride(
			name = "name",
			column = @Column(name = "guardian_name")
	),
	@AttributeOverride(
			name = "email",
			column = @Column(name = "guardian_email")
	),
	@AttributeOverride(
			name = "mobile",
			column = @Column(name = "guardian_mobile")
	)
})
public class Guardian {

	private String name;
	private String email;
	private String mobile;
}
