package com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//@Entity
public class RegisterDao {

	public static int save(String name,String email,String password,String gender,String dob,String addressLine,String city,String state,String country,String contact){

		EntityManagerFactory emf=Persistence.createEntityManagerFactory("dev");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Mailer m=new Mailer();
		
		
		
		int status=0;
		java.sql.Date sqlDOB=Formatter.getSqlDate(dob);
		try{
			m.setName(name);
			m.setEmail(email);
			m.setPassword(password);
			m.setGender(gender);
			m.setDob(dob);
			m.setAddressLine(addressLine);
			m.setCity(city);
			m.setState(state);
			m.setCountry(country);
			m.setContact(contact);
			m.setDate(Formatter.getCurrentDate());
			m.setStr("yes");
			
			status=m.getId();
			
			et.begin();
			em.persist(m);
			et.commit();
			
			status=m1();
			
			
		}catch(Exception e){
			
			
//			System.out.println(e);
		e.printStackTrace();	
		}	
		return status;
	}
	public static int m1()
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("dev");
		EntityManager em=emf.createEntityManager();
		Mailer m= em.find(Mailer.class,1);
		
	
		
		return m.getId();
		
	}
	
}
