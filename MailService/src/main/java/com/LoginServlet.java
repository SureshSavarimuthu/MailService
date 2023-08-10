package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean status=false;
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("dev");
		EntityManager em=emf.createEntityManager();
		
		
		Query q1  =em.createQuery("select s from Mailer s where s.email=?1 and s.password=?2");
		q1.setParameter(1, email);
		q1.setParameter(2, password);
		 Mailer q =(Mailer) q1.getSingleResult();

		if(q!=null){
			try{
				 
				 String email1 = q.getEmail();
				 String password1 = q.getPassword();
				 
				System.out.println(email1 +" "+password  );
				 
 				if(email.equals(q.getEmail()) && password.equals(q.getPassword()))
				{
					status=true;
				}					
			}
			catch(Exception e)
			{	
				System.out.println("catch  block is executed");
				System.out.println(e);
			}			
			if (status) 
				{
				out.print("you are successfully logged in!");
				request.getSession().setAttribute("login", "true");
				request.getSession().setAttribute("email", email);
				response.sendRedirect("InboxServlet");
				}
		}else{
			out.print("<p>Sorry, username or password error</p>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
