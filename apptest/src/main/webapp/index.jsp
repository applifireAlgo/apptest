<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title></title>
</head>
<body>
	<%
		String userAgent = request.getHeader("user-agent");
		if (userAgent != null && userAgent.matches(".*Mobile.*")) {
			request.getRequestDispatcher("/login.html").forward(request, response);
		} else {
			request.getRequestDispatcher("/login.html").forward(request, response);
		}
	%>
</body>
</html>