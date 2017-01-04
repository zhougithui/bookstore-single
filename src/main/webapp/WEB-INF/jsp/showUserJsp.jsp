<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/<s:theme code='styleSheet'/>" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<form action="/saveu" method="post">
		<input name="userName" />
		<input name="password" />
		<input type="submit" value="submit" />
	</form>
</body>
</html>