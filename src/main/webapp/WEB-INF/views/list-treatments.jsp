<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.10.2019
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <script> document.querySelector("#treatments").classList.add("active"); </script>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"><spring:message code="treatment.h1Title"/></h1>
            <h2 class="sub-header">
                <spring:message code="treatment.list_form_title"/>:
                <c:if test="${patientId gt 0}">${patient.lastName} ${patient.firstName}</c:if>
                <c:if test="${patientId eq 0}">All patients</c:if>
            </h2>

            <c:if test="${patientId gt 0}">
                <input type="button" value="<spring:message code="treatment.add_button"/>"
                       onclick="window.location.href='showForm?patientId=${param.patientId}'; return false;"
                    class="btn btn-primary"/>

                <c:if test="${treatments.size() > 0 eq true}">

                    <c:url var="dischargeLink" value="/patient/discharge">
                        <c:param name="patientId" value="${treatments[0].patientDto.patientId}"/>
                    </c:url>

                    <c:if test="${treatments[0].patientDto.readyToDischarge eq true}">
                        <input type="button" value="<spring:message code="treatment.discharge_button"/>" class="btn btn-primary"
                        onclick="window.location.href='../patient/discharge?patientId=${treatments[0].patientDto.patientId}'">
                     </c:if>

                    <c:if test="${treatments[0].patientDto.readyToDischarge eq false}">
                        <input type="button" value="<spring:message code="treatment.discharge_button"/>" class="btn btn-primary discharge-link"
                           discharge-patient-id="${treatments[0].patientDto.patientId}">
                    </c:if>
                </c:if>

            </c:if>
            <table class="table table-striped table-bordered">
            <c:set var="currentPatient" value="0" />
                      <c:forEach var="tempTreatment" items="${treatments}">

                        <c:url var="updateLink" value="/treatment/updateForm">
                            <c:param name="treatmentId" value="${tempTreatment.treatmentId}" />
                        </c:url>

                        <c:url var="deleteLink" value="/treatment/delete">
                            <c:param name="treatmentId" value="${tempTreatment.treatmentId}" />
                        </c:url>

                        <c:url var="generateLink" value="/treatment/generate">
                            <c:param name="treatmentId" value="${tempTreatment.treatmentId}" />
                            <c:param name="patientId" value="${tempTreatment.patientDto.patientId}" />
                        </c:url>

                        <c:url var="showLink" value="/event/list">
                            <c:param name="treatmentId" value="${tempTreatment.treatmentId}" />
                            <c:param name="patientId" value="${tempTreatment.patientDto.patientId}" />
                        </c:url>

            <c:if test="${currentPatient ne tempTreatment.patientDto.patientId}">
        </table>

            <c:if test="${patientId eq 0}">
                <p><c:out value="Patient: ${tempTreatment.patientDto.lastName} ${tempTreatment.patientDto.firstName}"/></p>
            </c:if>

            <table class="table table-striped table-bordered">
                <tr>
                    <th><spring:message code="treatment.time_pattern"/></th>
                    <th><spring:message code="treatment.description"/></th>
                    <th><spring:message code="treatment.period_start"/></th>
                    <th><spring:message code="treatment.period_end"/></th>
                    <th><spring:message code="treatment.remedy"/></th>
                    <th><spring:message code="treatment.dose"/></th>
                    <th><spring:message code="treatment.status"/></th>
                    <th><spring:message code="treatment.treatment_result"/></th>
                    <th><spring:message code="treatment.patient"/></th>
                    <th><spring:message code="treatment.actions"/></th>
                </tr>

                <c:set var="currentPatient" value="${tempTreatment.patientDto.patientId}" />
                </c:if>

                        <tr>
                            <td>${tempTreatment.patternDto.timePattern}</td>
                            <td>${tempTreatment.description}</td>
                            <td>${tempTreatment.period_start}</td>
                            <td>${tempTreatment.period_end}</td>
                            <td>${fn:trim(tempTreatment.remedy.name)}</td>
                            <td>${tempTreatment.dose} ${fn:trim(tempTreatment.remedy.unit)}</td>
                            <td>${tempTreatment.status.statusName}</td>
                            <td>${tempTreatment.treatmentResult}</td>
                            <td>${tempTreatment.patientDto.lastName}</td>

                            <td>
                                <!-- display the update link --> <a href="${updateLink}">Update</a> /
                                <a href="${deleteLink}"
                                     onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a> /

                                <c:if test="${tempTreatment.status eq 'PLANNED'}">
                                    <a href="${generateLink}">Generate events</a>
                                </c:if>
                                <c:if test="${tempTreatment.status ne 'PLANNED'}">
                                    <a href="${showLink}">Show events</a>
                                </c:if>
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
                <button id="dischargeBtn" type="button" class="btn btn-primary" data-dismiss="modal">Discharge forced</button>
            </div>
        </div>
    </div>
</div>

    <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/holder.min.js" />"></script>
    <script src="<c:url value="/resources/js/list-treatments.js" />"></script>
</body>
</html>