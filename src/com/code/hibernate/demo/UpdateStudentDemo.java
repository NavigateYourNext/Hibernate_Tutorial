package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Student;

public class UpdateStudentDemo 
{

	public static void main(String[] args) 
	{
		//Create Session Factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		
		
		//Create Session
		Session session = factory.getCurrentSession();
		
		try
		{
			int studentId = 10;
			
			//Begin Transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Retrieve the student based on id: primary key
			System.out.println("Getting student with id: "+studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating Student");
			myStudent.setFirstName("James");
			
			//Update all student email
			System.out.println("Updating all student email");
			session.createQuery("update Student set email = 'foobar@gmail.com'").executeUpdate();
			
			//Commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
		}
		finally
		{
			factory.close();
		}
		
	}

}
