<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="windows-1251" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="treatment.edit_form_title"/></title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/plugins/select/css/select2.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/top-bar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="parts/left-bar.jsp"/>
        <script> document.querySelector("#treatments").classList.add("active"); </script>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
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
                                <form:hidden path="treatmentId"/>
                                <form:hidden path="patientId"/>

                                <div class="form-group">
                                    <label class="col-md-3 control-label"><spring:message
                                            code="treatment.patient"/></label>
                                    <div class="col-md-9">
                                        <label class="form-control">${fn:trim(treatmentForm.patient.lastName)}</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="remedy.remedyId" class="col-md-3 control-label"><spring:message
                                            code="treatment.remedy"/></label>
                                    <div class="col-md-9">
                                        <form:select path="remedy.remedyId" cssClass="form-control">
                                            <c:forEach var='item' items='${remedies}'>
                                                <form:option value="${item.remedyId}" label="${item.name}"/>
                                            </c:forEach>
                                        </form:select>

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="timePattern.patternId" class="col-md-3 control-label"><spring:message
                                            code="treatment.time_pattern"/></label>
                                    <div class="col-md-9">
                                        <form:select path="timePattern.patternId" cssClass="form-control">
                                            <c:forEach var='item' items='${patterns}'>
                                                <form:option value="${item.patternId}" label="${item.timePattern}"/>
                                            </c:forEach>
                                        </form:select>
                                            <%--<form:input path="timePattern" cssClass="form-control" />--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="description" class="col-md-3 control-label"><spring:message
                                            code="treatment.description"/></label>
                                    <div class="col-md-9">
                                        <form:input path="description" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="period_start" class="col-md-3 control-label"><spring:message
                                            code="treatment.period_start"/></label>
                                    <div class="col-md-9">
                                        <form:input path="period_start" cssClass="form-control"
                                                    data-provide="datepicker"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="period_end" class="col-md-3 control-label"><spring:message
                                            code="treatment.period_end"/></label>
                                    <div class="col-md-9">
                                        <form:input path="period_end" cssClass="form-control"
                                                    data-provide="datepicker"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="dose" class="col-md-3 control-label"><spring:message
                                            code="treatment.dose"/></label>
                                    <div class="col-md-9">
                                        <form:input path="dose" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                <label for="status" class="col-md-3 control-label"><spring:message
                                        code="treatment.status"/></label>
                                <div class="col-md-9">
                                    <form:select path="status" cssClass="form-control">
                                        <c:forEach var='item' items='${statuses}'>
                                            <form:option value="${item}" label="${item.statusName}"/>
                                        </c:forEach>
                                    </form:select>
                                        <%--<form:input path="timePattern" cssClass="form-control" />--%>
                                </div>
                                </div>



                                <%--<div class="form-group">
                                    <label for="status" class="col-md-3 control-label"><spring:message
                                            code="treatment.status"/></label>
                                    <div class="col-md-9">
                                        <form:input path="status" cssClass="form-control"/>
                                    </div>
                                </div>--%>
                                <div class="form-group">
                                    <label for="treatmentResult" class="col-md-3 control-label"><spring:message
                                            code="treatment.treatment_result"/></label>
                                    <div class="col-md-9">
                                        <form:input path="treatmentResult" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <form:button cssClass="btn btn-primary"><spring:message
                                                code="treatment.submit"/></form:button>
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
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/plugins/select/js/select2.min.js" />"></script>
<%@ include file="datapicker.jsp" %>
<script src="<c:url value="/resources/js/treatment-form.js" />"></script>
</body>
</html>

