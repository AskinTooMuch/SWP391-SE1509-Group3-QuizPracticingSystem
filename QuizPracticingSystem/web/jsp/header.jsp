<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 20, 2021, 10:50:34 PM
    Menu of Homepage
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    20/9/21     1.0         TuanPAHE150543  First Deploy
    29/9/21     1.1         ChucNVHE150618  Add course content
--%>

<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Page</title>
        <link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
        <link rel='stylesheet' href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .dropdown-menu a :hover{
                color:red;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class="navbar-brand">
                    <img src="${contextPath}/images/logo-removebg-preview.png" alt="" width="80" height="40"></a>
                <div class="collapse navbar-collapse" id="1">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${contextPath}/index.jsp" style="font-weight: bold;">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${contextPath}/homeController?service=subjectList" style="font-weight: bold;">All Courses</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${contextPath}/marketingController?service=blogList" style="font-weight: bold;">Blog</a>
                        </li>
                        <li class="nav-item">
                            <p>          </p>
                        </li>
                        <c:if test="${(role.getUserRoleName().equalsIgnoreCase('admin')) || (role.getUserRoleName().equalsIgnoreCase('expert'))}">
                            <li class="nav-item">
                                <a class="nav-link" href="${contextPath}/subjectController?service=courseContentList" style="font-weight: bold;">Course Content List</a>
                            </li>
                        </c:if>
                        
                    </ul>
                    <form class="navbar-form navbar-right" role="search">
                        <div class="input-group">
                            <input type="text" class="form-control" style="border-radius: 5px" placeholder="">
                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-default">
                                    <span class="fas fa-search"></span>
                                </button>  
                            </span>
                        </div>
                    </form>

                    <c:choose>    
                        <%--When the user is not logged in--%>
                        <c:when test="${currUser == null}">
                            <div class="form-inline" style="float:right">
                                <a href="${contextPath}/login/login.jsp" class="btn btn-default "style="background-color:#5BC0DE;color:white;font-weight: bold;" 
                                   role="button" aria-pressed="true">Login</a>
                                <a href="${contextPath}/login/register.jsp" class="btn btn-default " style="background-color:#5BC0DE;color:white;font-weight: bold;"
                                   role="button" aria-pressed="true">Sign Up</a>
                            </div>
                        </c:when>
                        <%--When the user logged in--%>
                        <c:otherwise>
                            <button class="nav-item dropdown" style="background-color:#5BC0DE;color:white;font-weight: bold;border-radius: 5px">
                                <a class="nav-link dropdown-toggle"  data-toggle="dropdown" style="text-decoration: none;color:black;">
                                    <span class="fas fa-user-alt">Profile
                                    </span> 
                                </a>
                               
                                <div class="dropdown-menu" style="background-color:cyan;padding:10px 5px;">
                                    <a href="${contextPath}/login/userProfile.jsp" style="text-decoration: none;color:black">
                                        <span class="fas fa-info-circle">Your Info</span>  
                                    </a>
                                     <%-- Change user password--%>
                                    <a href="${contextPath}/login/changePassword.jsp" style="text-decoration: none;color:black">
                                        <span class="fas fa-unlock-alt">Change Password</span>  
                                    </a>
                                    <%-- If role is admin or owner--%>
                                    <c:if test="${currUser != null && role.getUserRoleName().equalsIgnoreCase('admin')}">
                                        <a href="#" style="text-decoration: none;color:black" >
                                            <span class="fas fa-user-shield">Admin Page</span>  
                                        </a>
                                    </c:if>
                                    <hr>
                                    <%-- Logout --%>
                                    <a href="${contextPath}/userController?service=logout" style="text-decoration: none;color:black" >
                                        <span class="fas fa-sign-out-alt">Log Out</span>   
                                    </a>
                                </div>
                            </button>
                        </c:otherwise>  
                    </c:choose>    
                </div>
            </div>
        </nav>


        <!--Jquery, JS-->
        <script src="https://kit.fontawesome.com/9650a62e47.js" crossorigin="anonymous"></script>
    </body>
</html>
