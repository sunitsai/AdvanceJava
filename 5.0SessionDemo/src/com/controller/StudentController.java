package com.controller;
/*
Controller is responsible to fetch data from view part (jsp pages)
and than Controller transfer these data to model part (dao) 
with the help of bean class object 

Here we fill our data into session object
we can get this data in any class 

we can remove this from session, generally session data 
is removed in logout.jsp

for this 

session.removeAttribute("student");
session.invalide();

will be used .


*/
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Student;
import com.dao.StudentDao;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("register"))
		{
			Student s=new Student();
			s.setFname(request.getParameter("fname"));
			s.setLname(request.getParameter("lname"));
			s.setEmail(request.getParameter("email"));
			s.setMobile(request.getParameter("mobile"));
			s.setPassword(request.getParameter("password"));
			StudentDao.insertStudent(s);
			response.sendRedirect("login.jsp");
		}
		else if(action.equalsIgnoreCase("login"))
		{
			Student s=new Student();
			s.setEmail(request.getParameter("email"));
			s.setPassword(request.getParameter("password"));
			Student student=StudentDao.checkLogin(s);
			
			if(request.getParameter("email").equalsIgnoreCase("") || request.getParameter("password").equalsIgnoreCase(""))
			{
				out.println("Your Email Or Password Is Missing");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
			else if(student==null)
			{
				out.println("Your Email Or Password Is Incorrect");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
			else
			{
				HttpSession session=request.getSession();
				session.setAttribute("student", student);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		}
	}

}
