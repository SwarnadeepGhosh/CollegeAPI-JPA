package com.swarna.collegeapi.guardian;

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
