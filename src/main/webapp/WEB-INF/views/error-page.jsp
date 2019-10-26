<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18.10.2019
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="windows-1251" %>
<html>
<head>
    <title>Улыбаемся и машем!</title>
</head>
<body>
	<!--img src="/reha2/resources/images/penguins.gif"-->
	<img src=${pageContext.request.contextPath}/resources/images/penguins.gif>
	<p>
		Error! ${errorMessage}
	</p>
	</body>
</html>
