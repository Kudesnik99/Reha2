<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.10.2019
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
   <div class="col-sm-3 col-md-2 sidebar">
       <ul class="nav nav-sidebar">
           <li class="active"><a href="${pageContext.request.contextPath}/patient/list"><spring:message code="leftbar.patient.patient_list"/> <span class="sr-only">(current)</span></a></li>
           <li><a href="${pageContext.request.contextPath}/treatment/list?patientId=0"><spring:message code="leftbar.treatment.list"/></a></li>
           <li><a href="${pageContext.request.contextPath}/event/list?treatmentId=0"><spring:message code="leftbar.event.list"/></a></li>
       </ul>
       <ul class="nav nav-sidebar">
           <li><a href="${pageContext.request.contextPath}/remedy/list"><spring:message code="leftbar.patient.remedy_list"/></a></li>
           <li><a href="${pageContext.request.contextPath}/pattern/list"><spring:message code="leftbar.pattern.pattern_list"/></a></li>
           <li><a href="${pageContext.request.contextPath}/user/list"><spring:message code="leftbar.user.user_list"/></a></li>
       </ul>

   </div>