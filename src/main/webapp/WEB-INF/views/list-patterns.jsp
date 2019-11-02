
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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="container">
                <div class="col-md-offset-1 col-md-10">
                    <h2><spring:message code="pattern.list_form_title"/></h2>
                    <hr />

                    <input type="button" value="<spring:message code="pattern.add_button"/>"
                           onclick="window.location.href='addForm'; return false;"
                           class="btn btn-primary" />
                    <br/><br/>
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title"><spring:message code="pattern.list_form_title"/></div>
                        </div>
                        <div class="panel-body">
                            <table class="table table-striped table-bordered">
                                <tr>
                                    <th><spring:message code="pattern.name"/></th>
                                    <th><spring:message code="pattern.template"/></th>
                                    <th><spring:message code="pattern.action"/></th>
                                </tr>

                                <!-- loop over and print our customers -->
                                <c:forEach var="tempPattern" items="${pattern}">

                                    <!-- construct an "update" link with customer id -->
                                    <c:url var="updateLink" value="/pattern/updateForm">
                                        <c:param name="patternId" value="${tempPattern.patternId}" />
                                    </c:url>

                                    <!-- construct an "delete" link with customer id -->
                                    <c:url var="deleteLink" value="/pattern/delete">
                                        <c:param name="patternId" value="${tempPattern.patternId}" />
                                    </c:url>

                                    <tr>
                                        <td>${tempPattern.timePattern}</td>
                                        <td>${tempPattern.patternTemplate}</td>

                                        <td>
                                            <!-- display the update link --> <a href="${updateLink}">Update</a> /
                                            <a href="${deleteLink}"
                                               onclick="if (!(confirm('<spring:message code="pattern.confirm"/>'))) return false">Delete</a>
                                        </td>

                                    </tr>

                                </c:forEach>

                            </table>

                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
