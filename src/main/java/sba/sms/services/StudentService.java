package sba.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		
		return false;
	}

	@Override
	public void registerStudentToCourse(String email, int courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Course> getStudentCourses(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
	

	
	 
	
}
