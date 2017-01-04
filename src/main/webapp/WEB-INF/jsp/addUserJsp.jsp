<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/<s:theme code='styleSheet'/>" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="cus" method="post" action="${ctx }/api/cus/saveCus/${flag}">     
        <form:errors path="*"></form:errors><br/><br/>
       	 客户名称：
      	<form:input path="cusName" /><br/>
        <form:errors path="cusName" cssClass="error" element="div"></form:errors><br/>
       	电话号码：
      	<form:input path="phone" /><br/>
        <form:errors path="phone" cssClass="error" element="div"></form:errors><br/>
        <input type="submit" value="Submit" />
        
    </form:form>  
</body>
</html>