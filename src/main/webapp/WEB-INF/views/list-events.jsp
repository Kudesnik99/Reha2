<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="windows-1251" %>
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
            <h1 class="page-header"><spring:message code="event.list_form_title"/></h1>
            <h2 class="sub-header">--------------------</h2>

            <%--input type="button" value="<spring:message code="patient.add_button"/>"
                   onclick="window.location.href='addPatient'; return false;"
                   class="btn btn-primary"/>
            <br/><br/--%>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th><spring:message code="event.date"/></th>
                        <th><spring:message code="event.status"/></th>
                        <th><spring:message code="event.reason"/></th>
                        <th><spring:message code="event.action"/></th>
                    </tr>

                    <c:forEach var="tempEvent" items="${events}">

                        <c:url var="updateLink" value="/patient/updateForm">
                            <c:param name="patientId" value="${tempEvent.eventId}"/>
                        </c:url>

                        <%--c:url var="deleteLink" value="/patient/delete">
                            <c:param name="patientId" value="${tempPatient.patientId}"/>
                        </c:url--%>

                        <tr>
                            <td>${tempEvent.dateTime}</td>
                            <td>${tempEvent.status}</td>
                            <td>${tempEvent.reason}</td>
                            <td>
                                <a href="${updateLink}">Update</a>
                            </td>
                        </tr>

                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/holder.min.js" />"></script>
</body>
