package sba.sms.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

}
