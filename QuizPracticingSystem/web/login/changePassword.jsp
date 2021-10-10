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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>

    <body>
        <%-- Check If user is logged in or not, if not redirect to index --%>
        <c:if test="${sessionScope.currUser == null}">
            <c:redirect url="/index.jsp"/>
        </c:if>
        <%-- Include header page --%>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="main">

            <%-- Login form --%>
            <div class="container" style="align-self: center; min-height: 50vh">
                
                    <div class="row">
                        <%-- Bootstrap to center form --%>
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <%-- Start form --%>
                            <form action="${contextPath}/userController" method="POST" name="changePassword" id="changePassword">
                            <%-- Enter old password --%>
                            <label class="label control-label">Old Password</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span></span>
                                <input type="password" class="form-control" name="oldPassword" placeholder="" required>
                            </div>
                            <%-- Enter new password --%>
                            <label class="label control-label">New Password</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="" onkeyup='check();' required>
                            </div>
                            <%-- Confirm new password --%>
                            <label class="label control-label">Confirm Password</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" class="form-control" name="rePassword" id="rePassword" placeholder="" onkeyup='check();' required>
                                
                            </div>
                            <span id='message'></span>
                            <br>
                            <%-- Submit form --%>
                            <div class="input-group">
                                <button type="submit" id="submit" class="btn btn-success">Change password!</button>
                                <input type="hidden" name="service" value="changePassword">
                            </div>
                            </form>
                            <%-- Display messages, if any --%>
                            <div>
                                <h5 style="color:${color}"><c:out value="${message}"/></h5>
                            </div>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
            </div>

        </div>
        <%-- Include footer page --%>
        <jsp:include page="/jsp/footer.jsp"/>
    </body>
</html>

<script>
    <%-- Check if new password and re-enter password match or not --%>
    var check = function () {
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
