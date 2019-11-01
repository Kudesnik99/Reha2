<%@ page import="java.awt.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <div class="container">
                <div class="col-md-offset-1 col-md-10">
                    <h2><spring:message code="pattern.list_form_title"/></h2>

                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title"><spring:message code="pattern.edit_form_title"/></div>
                        </div>
                        <div class="panel-body">
                            <form:form action="savePattern" cssClass="form-horizontal"
                                       method="post" modelAttribute="pattern">

                                <form:hidden path="patternId"/>

                                <div class="form-group">
                                    <label for="timePattern" class="col-md-3 control-label"><spring:message
                                            code="pattern.name"/></label>
                                    <div class="col-md-9">
                                        <form:input path="timePattern" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="patternTemplate" class="col-md-3 control-label"><spring:message
                                            code="pattern.template"/></label>
                                    <div class="col-md-9">
                                        <form:input path="patternTemplate" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <form:button cssClass="btn btn-primary"><spring:message
                                                code="pattern.submit"/></form:button>
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
