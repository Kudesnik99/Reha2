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
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<jsp:include page="parts/top-bar.jsp"/>
<div class="container">
    <div class="col-md-offset-1 col-md-10">
        <h2><spring:message code="treatment.list_form_title"/></h2>
        <hr />

        <%--
        <c:url var="updateLink" value="/patient/updateForm">
                            <c:param name="patientId" value="${tempPatient.patientId}"/>
                        </c:url>
        --%>

        <input type="button" value="<spring:message code="treatment.add_button"/>"
               onclick="window.location.href='showForm?patientId=${param.patientId}'; return false;"
               class="btn btn-primary"

        />
        <br/><br/>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title"><spring:message code="treatment.list"/></div>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th><spring:message code="treatment.time_pattern"/></th>
                        <th><spring:message code="treatment.description"/></th>
                        <th><spring:message code="treatment.period_start"/></th>
                        <th><spring:message code="treatment.period_end"/></th>
                        <th><spring:message code="treatment.dose"/></th>
                        <th><spring:message code="treatment.status"/></th>
                        <th><spring:message code="treatment.treatment_result"/></th>
                        <th><spring:message code="treatment.patient"/></th>
                    </tr>

                    <!-- loop over and print our customers -->
                    <c:forEach var="tempTreatment" items="${treatments}">

                        <!-- construct an "update" link with customer id -->
                        <c:url var="updateLink" value="/treatment/updateForm">
                            <c:param name="treatmentId" value="${tempTreatment.treatmentId}" />
                        </c:url>

                        <!-- construct an "delete" link with customer id -->
                        <c:url var="deleteLink" value="/treatment/delete">
                            <c:param name="treatmentId" value="${tempTreatment.treatmentId}" />
                        </c:url>

                        <tr>
                            <td>${tempTreatment.timePattern}</td>
                            <td>${tempTreatment.description}</td>
                            <td>${tempTreatment.period_start.toString()}</td>
                            <td>${tempTreatment.period_end.toString()}</td>
                            <td>${tempTreatment.dose.toString()}</td>
                            <td>${tempTreatment.status}</td>
                            <td>${tempTreatment.treatmentResult}</td>
                            <td>${tempTreatment.patient.lastName}</td>

                            <td>
                                <!-- display the update link --> <a href="${updateLink}">Update</a>
                                | <a href="${deleteLink}"
                                     onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
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