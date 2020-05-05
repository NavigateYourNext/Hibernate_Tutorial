package com.code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo 
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
			System.out.println("Creating a new Multiple Student object:\n");
			Student tempStudent1 = new Student("Will","Smith","willisok@gmail.com");
			Student tempStudent2 = new Student("Paul","Smith","paulisok@gmail.com");
			Student tempStudent3 = new Student("James","Smith","jamesisok@gmail.com");
			//Start a transaction
			session.beginTransaction();
			//Save the Student Object
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
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