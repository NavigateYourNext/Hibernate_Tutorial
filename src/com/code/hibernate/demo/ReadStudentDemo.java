package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Student;

public class ReadStudentDemo 
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
			Student tempStudent = new Student("Carry","Smith","carryisok@gmail.com");
			//Start a transaction
			session.beginTransaction();
			//Save the Student Object
			System.out.println(tempStudent);
			session.save(tempStudent);
			//Commit the Transaction
			session.getTransaction().commit();
			
			System.out.println("ID of Student Is: "+tempStudent.getId());
			System.out.println("Student Saved in DB");
			
			//Now get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//Retrieve Student bases on id: primary key
			System.out.println("\nGetting Student with Id: "+tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get Complete: "+myStudent);
			//Commit the transaction
			session.getTransaction().commit();
		}
		finally
		{
			factory.close();
		}
		
	}

}
