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
        <script> document.querySelector("#patients").classList.add("active"); </script>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"><spring:message code="patient.h1_title"/></h1>
            <input type="button" value="<spring:message code="patient.add_button"/>"
                   onclick="window.location.href='addPatient'; return false;"
                   class="btn btn-primary"/>
            <br/><br/>
            <%--div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title"><spring:message code="patient.list"/></div>
                </div>
                <div class="panel-body table-responsive"--%>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th><spring:message code="patient.first_name"/></th>
                        <th><spring:message code="patient.last_name"/></th>
                        <th><spring:message code="patient.diagnosis"/></th>
                        <th><spring:message code="patient.insurance_num"/></th>
                        <th><spring:message code="patient.discharged"/></th>
                        <th><spring:message code="patient.doctor"/></th>
                        <th><spring:message code="patient.email"/></th>
                        <th><spring:message code="patient.date_start"/></th>
                        <th><spring:message code="patient.date_finish"/></th>
                        <th><spring:message code="patient.action"/></th>
                    </tr>

                    <c:forEach var="tempPatient" items="${patients}">

                        <c:url var="updateLink" value="/patient/updateForm">
                            <c:param name="patientId" value="${tempPatient.patientId}"/>
                        </c:url>

                        <c:url var="dischargeLink" value="/patient/discharge">
                            <c:param name="patientId" value="${tempPatient.patientId}"/>
                        </c:url>

                        <c:url var="treatmentLink" value="/patient/treatments">
                            <c:param name="patientId" value="${tempPatient.patientId}"/>
                        </c:url>

                        <tr>
                            <td>${tempPatient.firstName}</td>
                            <td>${tempPatient.lastName}</td>
                            <td>${tempPatient.diagnosis}</td>
                            <td>${tempPatient.insuranceNum}</td>
                            <td><c:if test="${tempPatient.discharged eq true}"><spring:message
                                    code="patient.discharged"/></c:if>
                                <c:if test="${tempPatient.discharged eq false}"><spring:message
                                        code="patient.notdischarged"/></c:if>
                            </td>
                            <td>${fn:trim(userDto.lastName)}</td>
                            <td>${tempPatient.email}</td>
                            <td>${tempPatient.dateStart}</td>
                            <td>${tempPatient.dateFinish}</td>

                            <td>
                                <a href="${updateLink}">Update</a> /

                                <c:if test="${tempPatient.readyToDischarge eq true}">
                                    <a href="${dischargeLink}">Discharge</a> /
                                </c:if>

                                <c:if test="${tempPatient.readyToDischarge eq false}">
                                        <a href="#" class="discharge-link"
                                           discharge-patient-id="${tempPatient.patientId}">Discharge</a> /
                                </c:if>

                                <a href="${treatmentLink}">Treatments</a>
                            </td>

                        </tr>

                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Warning!</h4>
            </div>
            <div class="modal-body">
                <p><spring:message code="patient.dischargeConfirmMessage"/></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button id="editTreatmentBtn" type="button" class="btn btn-primary" data-dismiss="modal">Edit treatments</button>
                <button id="dischargeBtn" type="button" class="btn btn-primary" data-dismiss="modal">Discharge forced</button>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/holder.min.js" />"></script>
<script src="<c:url value="/resources/js/list-patient.js" />"></script>
</body>
</html>