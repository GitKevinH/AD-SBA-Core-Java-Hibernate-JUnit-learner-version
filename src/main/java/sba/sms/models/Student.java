package sba.sms.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "student")
public class Student {
	// fields
	@Id
	@NonNull
	@Column(columnDefinition = "varchar(50)")
	private String email;
	@NonNull
	@Column(columnDefinition = "varchar(50)")
	private String name;
	@NonNull
	@Column(columnDefinition = "varchar(50)")
	private String password;

	
	@ToString.Exclude
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
	@JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_email"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	 Set<Course> courses = new HashSet<>();
	
	
	public void addCourse(Course c) {
			courses.add(c);
			c.getStudents().add(this);
		}


	
	
	// tostring and hashcode and equals
	
	@Override
	public int hashCode() {
		return Objects.hash(email, name, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}
	
	


	
	
	
}
