<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <script> document.querySelector("#events").classList.add("active"); </script>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="container">
                <div class="col-md-offset-1 col-md-10">
                    <h2><spring:message code="event.form_name"/></h2>
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title"><spring:message code="event.form_title"/></div>
                        </div>
                        <div class="panel-body">
                            <form:form action="updateEvent" cssClass="form-horizontal"
                                       method="post" modelAttribute="eventDto">

                                <form:hidden path="eventId"/>
                                <form:hidden path="dateTime"/>
                                <form:hidden path="treatmentDto.treatmentId"/>

                                <div class="form-group">
                                    <label class="col-md-3 control-label"><spring:message
                                            code="event.date"/></label>
                                    <div class="col-md-9">
                                        <label class="form-control">${eventDto.dateTime}</label>
                                        <%--<form:input path="timePattern" cssClass="form-control"/>--%>
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
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="reason" class="col-md-3 control-label"><spring:message
                                            code="event.reason"/></label>
                                    <div class="col-md-9">
                                        <form:input path="reason" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <form:button cssClass="btn btn-primary"><spring:message
                                                code="event.submit"/></form:button>
                                    </div>
                                </div>
                            </form:form>
                        </div>

                    </div> <%-- Panel --%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
