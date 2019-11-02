<%@ page contentType="text/html; charset=UTF-8" pageEncoding="windows-1251" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="project.main_name"/></title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/top-bar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="parts/left-bar.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="container">
                <div class="col-md-offset-1 col-md-10">
                    <h2><spring:message code="remedy.list_form_title"/></h2>
                    <hr/>

                    <input type="button" value="<spring:message code="remedy.add_button"/>"
                           onclick="window.location.href='addRemedy'; return false;"
                           class="btn btn-primary"/>

                    <br/><br/>
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title"><spring:message code="remedy.list_form_title"/></div>
                        </div>
                        <div class="panel-body">
                            <table class="table table-striped table-bordered">
                                <tr>
                                    <th><spring:message code="remedy.name"/></th>
                                    <th><spring:message code="remedy.type"/></th>
                                    <th><spring:message code="remedy.units"/></th>
                                    <th><spring:message code="remedy.action"/></th>
                                </tr>

                                <!-- loop over and print our customers -->
                                <c:forEach var="tempRemedy" items="${remedies}">

                                    <!-- construct an "update" link with customer id -->
                                    <c:url var="updateLink" value="/remedy/updateRemedy">
                                        <c:param name="remedyId" value="${tempRemedy.remedyId}"/>
                                    </c:url>

                                    <!-- construct an "delete" link with customer id -->
                                    <c:url var="deleteLink" value="/remedy/delete">
                                        <c:param name="remedyId" value="${tempRemedy.remedyId}"/>
                                    </c:url>

                                    <tr>
                                        <td>${tempRemedy.name}</td>
                                        <td>${tempRemedy.type.typeName}</td>
                                        <td>${tempRemedy.unit}</td>

                                        <td>
                                            <!-- display the update link --> <a href="${updateLink}">Update</a>
                                            | <a href="${deleteLink}"
                                                 onclick="if (!(confirm('<spring:message
                                                         code="remedy.confirm"/>'))) return false">Delete</a>
                                        </td>

                                    </tr>

                                </c:forEach>

                            </table>

                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
