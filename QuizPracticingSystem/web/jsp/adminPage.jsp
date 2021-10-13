<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Oct 13, 2021, 11:37:42 AM
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    13/10/21    1.0         DuongNHHE150328 First Deploy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%-- Check If user is logged in or not, if not redirect to index --%>
        <c:if test="${sessionScope.currUser == null}">
            <c:redirect url="/index.jsp"/>
        </c:if>
        <%-- Include header page --%>
        <jsp:include page="/jsp/header.jsp"/>
        <c:if test="${ sessionScope.role.getUserRoleName().equalsIgnoreCase('admin') || sessionScope.role.getUserRoleName().equalsIgnoreCase('Expert')}">
            <a href="${contextPath}/jsp/quizDetail.jsp" style="text-decoration: none;color:black" >
                <span class="fas fa-user-shield">Quiz Detail Page</span>  
            </a>
            <br>
            <a href="${contextPath}/jsp/quizList.jsp" style="text-decoration: none;color:black" >
                <span class="fas fa-user-shield">Quiz List Page</span>  
            </a>
        </c:if>
        <c:if test="${ !sessionScope.role.getUserRoleName().equalsIgnoreCase('admin') && !sessionScope.role.getUserRoleName().equalsIgnoreCase('Expert')}">
            <h2 style="text-align: center;">You don't have the right to access this page</h2>
        </c:if>
        <%-- Include footer page --%>
        <jsp:include page="/jsp/footer.jsp"/>
    </body>
</html>
