<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/<s:theme code='styleSheet'/>" type="text/css" />
<script type="text/javascript" src="common/jquery/jquery.min.1.11.2.js"></script>
<!-- jsonp访问的一种方式 -->
<%-- <script type="text/javascript">  
    function jsonpCallback(result) {  
        alert(result); 
    }  
</script>  
<script type="text/javascript" src="http://127.0.0.1/bookstore-single/api/cus/getCusById/1?callback=jsonpCallback"></script> 
 --%>
<script type="text/javascript">
<!-- jsonp访问的另一种方式 -->
$.ajax({  
        url:"http://127.0.0.1/bookstore-single/api/cus/getCusById/1",  
        dataType:'jsonp',  
        data:'',  
        jsonp:'callback',  
        success:function(result) {  
            alert(result.cusName); 
        },  
        timeout:3000  
    });
</script>
</head>
<body>
	hello world.
</body>
</html>