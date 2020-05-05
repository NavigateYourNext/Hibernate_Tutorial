package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Student;

public class DeleteStudentDemo 
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
			
			//Delete the Student
			//session.delete(myStudent);
			
			//Delete the student another way
			session.createQuery("delete from Student where id=6").executeUpdate();
			
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
