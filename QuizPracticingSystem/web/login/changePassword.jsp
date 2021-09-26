<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 23, 2021, 10:08:58 PM
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    23/9/21     1.0         ChucNVHE150618  First Deploy
    26/9/21     1.0         ChucNVHE150618  Update frontend, add header and footer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password Page</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
        <link href="css/bootstrapp.min.css" rel="stylesheet">
        <script src="js/bootstrapp.min.js"></script>
        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>    
    </head>
    
    <body>
        <%-- Check If user is logged in or not, if not redirect to index --%>
        <c:if test="${sessionScope.currUser == null}">
            <c:redirect url="${contextPath}/index.jsp"/>
        </c:if>
        
        <jsp:include page="../jsp/Header.jsp"/>
        <div class="main">
            
        <%-- Login form --%>
            <div class="container" style="align-self: center">
                
                <form action="${contextPath}/userController" method="POST" name="changePassword" id="changePassword">
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <label class="label control-label">Old Password</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span></span>
                                <input type="password" class="form-control" name="oldPassword" placeholder="">
                            </div>
                            <label class="label control-label">New Password</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="" onkeyup='check();'>
                            </div>
                            <label class="label control-label">Confirm Password</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" class="form-control" name="rePassword" id="rePassword" placeholder="" onkeyup='check();'>
                                <span id='message'></span>
                            </div>
                            <div class="input-group">
                                <button type="submit" id="submit" class="btn btn-default">Change password!</button>
                                <input type="hidden" name="service" value="changePassword">
                            </div>
                            <div>
                                <h4 style="color:${color}"> <c:out value="${message}"/> </h4>
                            </div>
                        </div>
                    </div>
                </form> 
            </div>
                            
        </div>
        <jsp:include page="../jsp/Footer.jsp"/>
    </body>
</html>

<script>
    <%-- Check if new password and re-enter password match or not --%>
    var check = function() {
        if (document.getElementById('newPassword').value == document.getElementById('rePassword').value) {
          document.getElementById('message').style.color = 'green';
          document.getElementById('message').innerHTML = 'Valid';
           document.getElementById('submit').disabled = false;
        } else {
          document.getElementById('message').style.color = 'red';
          document.getElementById('message').innerHTML = 'New password dont match';
           document.getElementById('submit').disabled = true;
        }
      }
</script>
