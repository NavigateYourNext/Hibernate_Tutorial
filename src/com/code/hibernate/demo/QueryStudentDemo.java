package com.code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.code.hibernate.demo.entity.Student;

public class QueryStudentDemo 
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
			session.beginTransaction();
			
			//Query Students
			
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//Display the students
			System.out.println("List of All Students:");
			displayStudents(theStudents);
			
			//Query Students with last name Smith
			System.out.println("Students with LastName: Smith");
			theStudents = session.createQuery("from Student s where s.lastName='Smith'").getResultList();
			displayStudents(theStudents);
			
			//Query Students with lastName Smith and firstName Carry
			System.out.println("Students with LastName: Smith and FirstName: Carry");
			theStudents = session.createQuery("from Student s where s.lastName='Smith' AND s.firstName='Carry'").getResultList();
			displayStudents(theStudents);
			
			//Query those student whos email ends with @gmail.com
			System.out.println("Students with email ends with @gmail.com");
			theStudents = session.createQuery("from Student s where s.email like '%@gmail.com'").getResultList();
			displayStudents(theStudents);
			
			//Commit Transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
		}
		finally
		{
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent:theStudents)
			System.out.println(tempStudent);
	}

}
