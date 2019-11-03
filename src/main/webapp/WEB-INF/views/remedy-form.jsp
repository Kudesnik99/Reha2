<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="windows-1251" %>%>
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
        <script> document.querySelector("#remedies").classList.add("active"); </script>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="container">
                <div class="col-md-offset-1 col-md-10">
                    <h2 class="text-center"><spring:message code="remedy.add_form_title"/></h2>
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title"><spring:message code="remedy.add_button"/></div>
                        </div>
                        <div class="panel-body">
                            <form:form action="saveRemedy" cssClass="form-horizontal"
                                       method="post" modelAttribute="remedy">

                                <!-- need to associate this data with remedy id -->
                                <form:hidden path="remedyId"/>

                                <div class="form-group">
                                    <label for="name" class="col-md-3 control-label"><spring:message
                                            code="remedy.name"/></label>
                                    <div class="col-md-9">
                                        <form:input path="name" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="type" class="col-md-3 control-label"><spring:message
                                            code="remedy.type"/></label>
                                    <div class="col-md-9">
                                        <form:select path="type" cssClass="form-control">
                                            <c:forEach var='item' items='${remedyTypes}'>
                                                <form:option value="${item}" label="${item.typeName}"/>
                                            </c:forEach>
                                        </form:select>
                                            <%--<form:input path="timePattern" cssClass="form-control" />--%>
                                    </div>
                                </div>


                                <%--<div class="form-group">
                                    <label for="type" class="col-md-3 control-label"><spring:message
                                            code="remedy.type"/></label>
                                    <div class="col-md-9">
                                        <form:input path="type" cssClass="form-control"/>
                                    </div>
                                </div>--%>

                                <div class="form-group">
                                    <label for="unit" class="col-md-3 control-label"><spring:message
                                            code="remedy.units"/></label>
                                    <div class="col-md-9">
                                        <form:input path="unit" cssClass="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <form:button cssClass="btn btn-primary"><spring:message
                                                code="remedy.submit"/></form:button>
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
