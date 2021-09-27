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
        <c:out value="${currUser.getProfilePic()}" ></c:out>
        <img src="${contextPath}/upload/${currUser.getProfilePic()}">
        <object style="border: solid #f0f2f5 white; border-radius: 15px; " class="avatar_lagre"
                                    data="${contextPath}/upload/${currUser.getProfilePic()}" 
                                    alt="" width="190" height="190"></object>
    </body>
</html>
