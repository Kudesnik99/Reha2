<%@ page contentType="text/html; charset=UTF-8" pageEncoding="windows-1251" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="user.edit_form_title"/></title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/top-bar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="parts/left-bar.jsp"/>
        <script> document.querySelector("#users").classList.add("active"); </script>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="container">
                <div class="col-md-offset-2 col-md-7">
                    <h2 class="text-center"><spring:message code="user.add_form_title"/></h2>
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title"><spring:message code="user.add_button"/></div>
                        </div>
                        <div class="panel-body">
                            <form:form action="saveUser" cssClass="form-horizontal"
                                       method="post" modelAttribute="user">

                                <!-- need to associate this data with customer id -->
                                <form:hidden path="userId"/>

                                <div class="form-group">
                                    <label for="firstName" class="col-md-3 control-label"><spring:message
                                            code="user.first_name"/></label>
                                    <div class="col-md-9">
                                        <form:input path="firstName" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastName" class="col-md-3 control-label"><spring:message
                                            code="user.last_name"/></label>
                                    <div class="col-md-9">
                                        <form:input path="lastName" cssClass="form-control"/>
                                        <form:errors path="lastName" cssClass="help-block"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="role" class="col-md-3 control-label"><spring:message
                                            code="user.role"/></label>
                                    <div class="col-md-9">
                                        <form:input path="role" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="email" class="col-md-3 control-label"><spring:message
                                            code="user.email"/></label>
                                    <div class="col-md-9">
                                        <form:input path="email" cssClass="form-control"/>
                                        <form:errors path="email" cssClass="help-block"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label"><spring:message
                                            code="user.password"/></label>
                                    <div class="col-md-9">
                                        <form:input path="password" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <form:button cssClass="btn btn-primary"><spring:message
                                                code="user.submit"/></form:button>
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
</body>
</html>

