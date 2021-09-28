<%-- 
    Document   : userProfile
    Created on : Sep 26, 2021, 2:40:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/jsp/Header.jsp"/>

        <object style="border: solid #f0f2f5 white; border-radius: 15px; " class="avatar_lagre"
                data="${contextPath}/upload/${ currUser.getProfilePic() }" 
                alt="" width="190" height="190"></object>
        <p>Name: <c:out value="${ currUser.getUserName()}"/></p>
        <p>Mail: <c:out value="${ currUser.getUserMail()}"/></p>
        <p>Mobile: <c:out value="${ currUser.getUserMobile()}"/></p>
        <c:if test="${currUser.isGender()}">
            <p>Gender: Male</p>
        </c:if>
        <c:if test="${!currUser.isGender()}">
            <p>Gender: Female</p>
        </c:if>
        <jsp:include page="/jsp/Footer.jsp"/>
    </body>
</html>
