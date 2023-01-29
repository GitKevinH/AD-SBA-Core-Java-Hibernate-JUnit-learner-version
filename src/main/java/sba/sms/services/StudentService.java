package sba.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

public class StudentService implements StudentI {

	@Override
	public List<Student> getAllStudents() {
		
		List<Student> listStud = new ArrayList<>();
		// open the connection
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			listStud = session.createQuery("from student", Student.class).getResultList();  // HQL Query to add to the list from the db
			
			tx.commit(); // added this commit because it was required in the README, from my understanding,
						 //      because this is just creating a query, it isn't actually needed.
			
		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();   // added this rollback because it was required in the README, from my understanding,
						     //      because this is just creating a query, it isn't actually needed.
			
		} finally {
			session.close();
		}
		
		return listStud;
	}

	@Override
	public void createStudent(Student student) { // need to make this persist student to database
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			session.persist(student);
			
			tx.commit(); 
							

		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
							

		} finally {
			session.close();
		}

	}

	@Override
	public Student getStudentByEmail(String email) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Student stdnt = null;
		
		try {
			tx = session.beginTransaction();
			
			stdnt = session.get(Student.class,email);
			
			
			tx.commit(); 
							

		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
							

		} finally {
			session.close();
		}
		
		return stdnt;
	}

	@Override
	public boolean validateStudent(String email, String password) {
		
	
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Student stdnt = new Student();
		Boolean confirm = false;
		
		try {
			tx = session.beginTransaction();
			
			stdnt = getStudentByEmail(email);
			
			if(stdnt.getPassword().equals(password)) {
				confirm = true;
			}
			
			tx.commit(); 
							

		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
			
		}catch (NullPointerException ex) {
			System.out.println("Student information invalid");
			tx.rollback();

		} finally {
			session.close();
		}
		
		return confirm;
	}
	
	private static final CourseService courseService = new CourseService();
	
	@Override
	public void registerStudentToCourse(String email, int courseId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Student stdnt = new Student();
		
		try {
			tx = session.beginTransaction();
			
			stdnt = getStudentByEmail(email);
			
			stdnt.addCourse(courseService.getCourseById(courseId));
			
			session.merge(stdnt);
			
			
			tx.commit(); 
							

		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
							

		} finally {
			session.close();
		}
		
	}

	@Override
	public List<Course> getStudentCourses(String email) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Course> courseList = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			
			String nativeQuery = "SELECT course.id, course.name, course.instructor FROM course JOIN student_courses ON course.id = student_courses.course_id JOIN student ON student_courses.student_email = student.email WHERE student.email= :email";
			NativeQuery<Course> query = session.createNativeQuery(nativeQuery, Course.class);
			query.setParameter("email", email);
			courseList = query.getResultList();
			
			
			
			tx.commit(); 
							

		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
							

		} finally {
			session.close();
		}
		
		return courseList;
	}

	
	

	
	

	
	 
	
}
