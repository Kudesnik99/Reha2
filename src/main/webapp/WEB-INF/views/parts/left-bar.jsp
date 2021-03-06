<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script src="<c:url value="/resources/js/left-bar.js" />"></script>
   <div class="col-sm-3 col-md-2 sidebar">
       <ul class="nav nav-sidebar">
           <c:if test="${userDto.role.getRoleName() eq 'Doctor'}">
           <li id="patients" ><a href="${pageContext.request.contextPath}/patient/list"><spring:message code="leftbar.patient.patient_list"/> <span class="sr-only">(current)</span></a></li>
           <li id="treatments"><a href="${pageContext.request.contextPath}/treatment/list?patientId=0"><spring:message code="leftbar.treatment.list"/></a></li>
           <li id="events"   ><a href="${pageContext.request.contextPath}/event/list?treatmentId=0"><spring:message code="leftbar.event.list"/></a></li>
           </c:if>
           <c:if test="${userDto.role.getRoleName() eq 'Nurse'}">
               <li id="patients" ><a href="${pageContext.request.contextPath}/patient/list"><spring:message code="leftbar.patient.patient_list"/> <span class="sr-only">(current)</span></a></li>
               <li id="treatments"><a href="${pageContext.request.contextPath}/treatment/list?patientId=0"><spring:message code="leftbar.treatment.list"/></a></li>
               <li id="events"   ><a href="${pageContext.request.contextPath}/event/list?treatmentId=0"><spring:message code="leftbar.event.list"/></a></li>
           </c:if>
       </ul>
       <ul class="nav nav-sidebar">
           <c:if test="${userDto.role.getRoleName() eq 'Administrator'}">
           <li id="remedies" ><a href="${pageContext.request.contextPath}/remedy/list"><spring:message code="leftbar.patient.remedy_list"/></a></li>
           <li id="patterns" ><a href="${pageContext.request.contextPath}/pattern/list"><spring:message code="leftbar.pattern.pattern_list"/></a></li>
           <li id="users"    ><a href="${pageContext.request.contextPath}/user/list"><spring:message code="leftbar.user.user_list"/></a></li>
           </c:if>
           <c:if test="${userDto.role.getRoleName() eq 'Doctor'}">
           <li id="remedies" ><a href="${pageContext.request.contextPath}/remedy/list"><spring:message code="leftbar.patient.remedy_list"/></a></li>
           </c:if>
       </ul>

   </div>