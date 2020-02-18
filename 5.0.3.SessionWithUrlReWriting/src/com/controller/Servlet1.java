package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n = request.getParameter("username");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("Welcome : "+n);
			/*
here data will be strored hidden form field	
	*/
		out.print("<form action='Servlet2' method='post'>");
		out.println("<input type='hidden' name='uname' value='"+n+"'>"); 
		out.print("<input type='submit' >");
		out.print("</form>");
	}
}
