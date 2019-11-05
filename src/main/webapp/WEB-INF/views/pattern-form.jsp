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
        <script> document.querySelector("#patterns").classList.add("active"); </script>
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

                            <br/>
                            <p> The pattern is a list of six single space-separated fields: representing second, minute,
                                hour, day, month, weekday. Month and weekday names can be given as the first three letters
                                of the English names.
                            </p>

                            <p> Example patterns:
                            </p>

                            <p>    "0 0 * * * *" = the top of every hour of every day.                        </p>
                            <p>    "*/10 * * * * *" = every ten seconds.                                      </p>
                            <p>    "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.                       </p>
                            <p>    "0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.                          </p>
                            <p>    "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.   </p>
                            <p>    "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays                 </p>
                            <p>    "0 0 0 25 12 ?" = every Christmas Day at midnight                          </p>

                        </div>

                    </div> <%-- Panel --%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
