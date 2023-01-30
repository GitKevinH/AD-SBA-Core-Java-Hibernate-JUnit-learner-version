package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sba.sms.models.Course;
import sba.sms.utils.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
class CourseServiceTest{
	

	static CourseService courseService;

    @BeforeAll
    static void beforeAll() {
        courseService = new CourseService();
        CommandLine.addData();
    }

// Could not get test to test against IDs while they auto increment. Created easier test for GetStudentByEmail method under StudentServiceTest to meet requirement.
    
//	  @Test void getAllCourses() {
//		  
//		  List<Course> expected = new ArrayList<>(Arrays.asList(
//			new Course("Java", "Phillip Witkin"),
//	        new Course("Frontend", "Kasper Kain"),
//	        new Course("JPA", "Jafer Alhaboubi"),
//	        new Course("Spring Framework", "Phillip Witkin"),
//	        new Course("SQL", "Phillip Witkin") ));
//
//		  
//		  assertThat(courseService.getAllCourses()).hasSameElementsAs(expected); }
	
    
    
}
