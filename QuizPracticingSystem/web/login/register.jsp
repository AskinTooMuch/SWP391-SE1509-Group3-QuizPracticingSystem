<%-- 
    Document   : register
    Created on : Sep 21, 2021, 10:25:35 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="${contextPath}/css/stylelogin.css" media="screen">
        <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">

    </head>
    <body class="u-body"><header class="u-align-left u-clearfix u-header u-header" id="sec-ad7d"><div class="u-clearfix u-sheet u-sheet-1">
                <c:if test="${mess != null}">
                    <c:out value="${mess}"></c:out>
                </c:if>
                <img class="u-image u-image-default u-preserve-proportions u-image-1" src="${contextPath}/images/login/logologin2.png" alt="" data-image-width="210" data-image-height="92">
                <img class="u-image u-image-default u-image-2" src="${contextPath}/images/login/logo-login.png" alt="" data-image-width="492" data-image-height="93">

                <div class="u-form u-form-1">
                    <form action="${contextPath}/userController" method="POST" class="u-clearfix u-form-spacing-15 u-form-vertical u-inner-form" style="padding: 15px;" source="custom" name="form">

                        <div class="u-form-group u-form-name">
                            <p>Enter your user name:</p>
                            <input type="text" placeholder="Username"  name="userName" class="u-border-1 u-border-grey-30 u-input u-input-rectangle" required="">
                        </div>
                        <div class="u-form-group u-form-name">
                            <p>Enter your password:</p>
                            <input type="password" placeholder="Password"  name="password" class="u-border-1 u-border-grey-30 u-input u-input-rectangle" required="">
                        </div>
                        <div class="u-form-group u-form-name">
                            <p>Confirm your password:</p>
                            <input type="password" placeholder="Confirm password"  name="confirmPass" class="u-border-1 u-border-grey-30 u-input u-input-rectangle" required="">
                        </div>
                        <div class="u-form-group u-form-name">
                            <p>Enter your email:</p>
                            <input type="email" placeholder="Email"  name="userMail" class="u-border-1 u-border-grey-30 u-input u-input-rectangle" required="">
                        </div>
                        <div class="u-form-group u-form-name">
                            <p>Enter your phone:</p>
                            <input type="text" placeholder="Phone"  name="userMobile" class="u-border-1 u-border-grey-30 u-input u-input-rectangle" required="">
                        </div>
                        <div class="u-form-group u-form-name">
                            <p>Gender:</p>
                            <input type="radio" name="gender" value="Male"> Male
                            <br>
                            <input type="radio" name="gender" value="Female"> Female
                        </div>

                        <div class="u-align-center u-form-group u-form-submit">

                            <input type="hidden" name="service" value="register">

                            <button class="u-btn u-btn-submit u-button-style" type="submit">Register</button>
                        </div>
                    </form>
                </div>
                </body>
                </html>
