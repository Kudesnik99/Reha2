<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.10.2019
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>javaguides.net</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />"
          rel="stylesheet">
</head>
<body>
<jsp:include page="parts/top-bar.jsp"/>
<div class="container">
    <div class="col-md-offset-1 col-md-10">
        <h2><spring:message code="user.list_form_title"/></h2>
        <hr />

        <input type="button" value="<spring:message code="user.add_button"/>"
               onclick="window.location.href='showForm'; return false;"
               class="btn btn-primary" />
        <br/><br/>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title"><spring:message code="user.list"/></div>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th><spring:message code="user.first_name"/></th>
                        <th><spring:message code="user.last_name"/></th>
                        <th><spring:message code="user.role"/></th>
                        <th><spring:message code="user.email"/></th>
                        <th><spring:message code="user.password"/></th>
                        <th><spring:message code="user.action"/></th>
                    </tr>

                    <!-- loop over and print our customers -->
                    <c:forEach var="tempUser" items="${users}">

                        <!-- construct an "update" link with customer id -->
                        <c:url var="updateLink" value="/user/updateForm">
                            <c:param name="userId" value="${tempUser.userId}" />
                        </c:url>

                        <!-- construct an "delete" link with customer id -->
                        <c:url var="deleteLink" value="/user/delete">
                            <c:param name="userId" value="${tempUser.userId}" />
                        </c:url>

                        <tr>
                            <td>${tempUser.firstName}</td>
                            <td>${tempUser.lastName}</td>
                            <td>${tempUser.role}</td>
                            <td>${tempUser.email}</td>
                            <td>${tempUser.password}</td>

                            <td>
                                <!-- display the update link --> <a href="${updateLink}">Update</a>
                                | <a href="${deleteLink}"
                                     onclick="if (!(confirm('<spring:message code="user.confirm"/>'))) return false">Delete</a>
                            </td>

                        </tr>

                    </c:forEach>

                </table>

            </div>
        </div>
    </div>

</div>
</body>
</html>