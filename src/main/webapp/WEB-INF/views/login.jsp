<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.10.2019
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 2 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">

        <title><spring:message code="user.signin_title"/></title>

        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

        <!-- Bootstrap core CSS -->
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="<c:url value="/resources/css/ie10-viewport-bug-workaround.css" />" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="<c:url value="/resources/css/signin.css" />" rel="stylesheet">
    </head>

    <body>

        <div class="container">

            <form class="form-signin"  action="<c:url value="/login"/>" method="post">
                <h2 class="form-signin-heading"><spring:message code="user.signin_mess"/></h2>
                <label for="inputEmail" class="sr-only"><spring:message code="user.email"/></label>
                <input name="email" type="" id="inputEmail" class="form-control" placeholder="<spring:message code="user.email"/>"
                       required autofocus>
                <label for="inputPassword" class="sr-only"><spring:message code="user.password"/></label>
                <input name="password" type="password" id="inputPassword" class="form-control"
                       placeholder="<spring:message code="user.password"/>" required>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> <spring:message code="user.remember"/>
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message
                        code="user.signin_button"/></button>
            </form>

        </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js" />"></script>
    </body>
</html>