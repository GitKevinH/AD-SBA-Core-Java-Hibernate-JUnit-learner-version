package sba.sms.models;

import java.util.HashSet;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	@Column (columnDefinition = "varchar(50)")
	private String name;
	@NonNull
	@Column (columnDefinition = "varchar(50)")
	private String instructor;
	
	 @ToString.Exclude
	 @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "courses")
	 Set<Student> students = new HashSet<>();

	
	
	// Hashcode and equals
	
	@Override
	public int hashCode() {
		return Objects.hash(id, instructor, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return id == other.id && Objects.equals(instructor, other.instructor) && Objects.equals(name, other.name);
	}
}


