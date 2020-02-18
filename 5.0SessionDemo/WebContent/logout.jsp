<%
	session.removeAttribute("student");
	session.invalidate();
	response.sendRedirect("login.jsp");
%>