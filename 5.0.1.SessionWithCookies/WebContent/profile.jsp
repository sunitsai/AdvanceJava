<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Cookie cks[] = request.getCookies();
	
if(cks!=null){			
	for(Cookie ck :cks){
		if(ck.getName().equals("user")){
			out.print("Welcome "+ck.getValue());
		}
	}
}	
%>
<br>


</body>
</html>