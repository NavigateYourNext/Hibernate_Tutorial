package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Student;

public class CreateStudentDemo 
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
			//Use the session object to save Java Object
			System.out.println("Creating a new Student object:\n");
			Student tempStudent = new Student("Tomy","Smith","tomisok@gmail.com");
			//Start a transaction
			session.beginTransaction();
			//Save the Student Object
			session.save(tempStudent);
			//Commit the Transaction
			session.getTransaction().commit();
			
			System.out.println("Student Saved in DB");
		}
		finally
		{
			factory.close();
		}
		
	}

}
