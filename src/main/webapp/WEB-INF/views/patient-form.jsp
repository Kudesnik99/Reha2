<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="windows-1251" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="patient.edit_form_title"/></title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
    <%@ include file="datapicker.jsp" %>
</head>
<body>
<jsp:include page="parts/top-bar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="parts/left-bar.jsp"/>
        <script> document.querySelector("#patients").classList.add("active"); </script>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="container">
                <div class="col-md-offset-1 col-md-10">
                    <h2 class="text-center"><spring:message code="patient.edit_form_title"/></h2>
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title"><spring:message code="patient.edit_form_title"/></div>
                        </div>
                        <div class="panel-body">
                            <form:form action="savePatient" cssClass="form-horizontal"
                                       method="post" modelAttribute="patient">

                                <!-- need to associate this data with customer id -->
                                <form:hidden path="patientId"/>
                                <form:hidden path="readyToDischarge"/>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">Doctor:</label>
                                    <div class="col-md-9">
                                        <label class="col-md-3 control-label">${fn:trim(userDto.lastName)}</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="firstName" class="col-md-3 control-label"><spring:message
                                            code="patient.first_name"/></label>
                                    <div class="col-md-9">
                                        <form:input path="firstName" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastName" class="col-md-3 control-label"><spring:message
                                            code="patient.last_name"/></label>
                                    <div class="col-md-9">
                                        <form:input path="lastName" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="diagnosis" class="col-md-3 control-label"><spring:message
                                            code="patient.diagnosis"/></label>
                                    <div class="col-md-9">
                                        <form:input path="diagnosis" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="email" class="col-md-3 control-label"><spring:message
                                            code="patient.email"/></label>
                                    <div class="col-md-9">
                                        <form:input path="email" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="insuranceNum" class="col-md-3 control-label"><spring:message
                                            code="patient.insurance_num"/></label>
                                    <div class="col-md-9">
                                        <form:input path="insuranceNum" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="discharged"
                                           class="custom-control-label col-md-3 control-label"><spring:message
                                            code="patient.discharged"/></label>
                                    <div class="custom-control custom-checkbox col-md-9">
                                        <form:checkbox path="discharged" cssClass="custom-control-input"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="dateStart" class="col-md-3 control-label"><spring:message
                                            code="patient.date_start"/></label>
                                    <div class="col-md-9">
                                        <form:input path="dateStart" cssClass="form-control" data-provide="datepicker"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="dateFinish" class="col-md-3 control-label"><spring:message
                                            code="patient.date_finish"/></label>
                                    <div class="col-md-9">
                                        <form:input path="dateFinish" cssClass="form-control"
                                                    data-provide="datepicker"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <form:button cssClass="btn btn-primary"><spring:message
                                                code="patient.submit"/></form:button>
                                    </div>
                                </div>

                            </form:form>



                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/jquery-3.4.1.slim.min.js" />"></script>
<script>
    $.fn.datepicker.defaults.format = "dd-mm-yyyy";
    $('.datepicker').datepicker();
</script>
</body>
</html>

