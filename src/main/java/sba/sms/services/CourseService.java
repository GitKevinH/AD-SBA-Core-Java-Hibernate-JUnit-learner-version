package sba.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

public class CourseService implements CourseI {

	@Override
	public void createCourse(Course course) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			session.persist(course);

			tx.commit();

		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();

		} finally {
			session.close();
		}

	}

	@Override
	public Course getCourseById(int courseId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		Course course = new Course();
		try {
			tx = session.beginTransaction();
			
			Query<Course> query = session.createQuery("from Course where id =:id", Course.class).setParameter("id", courseId);
			
			course = query.getSingleResult();
	
			tx.commit();

		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();

		} finally {
			session.close();
		}
		return course;
	}

	@Override
	public List<Course> getAllCourses() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Course> courseList = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			
			courseList = session.createQuery("from Course ", Course.class).getResultList();
			

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
