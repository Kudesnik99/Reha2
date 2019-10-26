<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.10.2019
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="windows-1251"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="treatment.edit_form_title"/></title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.4.1.slim.min.js" />"></script>
    <%@ include file="datapicker.jsp" %>
</head>
<body>
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center"><spring:message code="treatment.add_form_title"/></h2>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title"><spring:message code="treatment.add_button"/></div>
            </div>
            <div class="panel-body">
                <form:form action="saveTreatment" cssClass="form-horizontal"
                           method="post" modelAttribute="treatmentForm">

                    <!-- need to associate this data with treatment id -->
                    <form:hidden path="treatmentId" />
                    <form:hidden path="patientId" />

                    <div class="form-group">
                        <label class="col-md-3 control-label">Patient:</label>
                        <div class="col-md-9">
                            <label class="form-control">${fn:trim(treatmentForm.patient.lastName)}</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="timePattern" class="col-md-3 control-label"><spring:message code="treatment.time_pattern"/></label>
                        <div class="col-md-9">
                            <form:input path="timePattern" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-3 control-label"><spring:message code="treatment.description"/></label>
                        <div class="col-md-9">
                            <form:input path="description" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="period_start" class="col-md-3 control-label"><spring:message code="treatment.period_start"/></label>
                        <div class="col-md-9">
                            <form:input path="period_start" cssClass="form-control" data-provide="datepicker"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="period_start" class="col-md-3 control-label"><spring:message code="treatment.period_end"/></label>
                        <div class="col-md-9">
                            <form:input path="period_end" cssClass="form-control" data-provide="datepicker"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dose" class="col-md-3 control-label"><spring:message code="treatment.dose"/></label>
                        <div class="col-md-9">
                            <form:input path="dose" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="status" class="col-md-3 control-label"><spring:message code="treatment.status"/></label>
                        <div class="col-md-9">
                            <form:input path="status" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="treatmentResult" class="col-md-3 control-label"><spring:message code="treatment.treatment_result"/></label>
                        <div class="col-md-9">
                            <form:input path="treatmentResult" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <form:button cssClass="btn btn-primary"><spring:message code="treatment.submit"/></form:button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
  <script>
    $.fn.datepicker.defaults.format = "dd-mm-yyyy";
    $('.datepicker').datepicker();
  </script>
</body>
</html>

