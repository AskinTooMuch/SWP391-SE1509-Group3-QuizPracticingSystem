<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Oct 5, 2021, 5:00:44 PM
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    05/10/21    1.0         DuongNHHE150328 First Deploy
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
        <c:if test="${registedSubject == null}">
            <c:redirect url="/quizController?service=getPracticeDetail"/>
        </c:if>
        <c:set var="subjectDimension" value="2"/>
        
        <%-- Include header page --%>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="main">

            <%-- Login form --%>
            <div class="container" style="align-self: center; min-height: 50vh">
                <%-- Start form --%>
                <form action="${contextPath}/userController" method="POST" name="changePassword" id="changePassword">
                    <div class="row">
                        <%-- Bootstrap to center form --%>
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <h1>Practice Detail</h1>
                            <label class="label control-label">Subject</label>
                            <div class="form-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                                <select class="form-control" name="subject">
                                    <c:forEach items="${registedSubject}" var="subject">
                                        <option value="${ subject.getSubjectId()}" onclick=""><c:out value="${subject.getSubjectName()}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                                <%--<select class="form-control" name="subject">
                                    <c:forEach items="${subjectDimension}" var="dimesion">
                                        <option value="${ subject.getSubjectId()}"><c:out value="${subject.getSubjectName()}" /></option>
                                    </c:forEach>
                                </select> --%>
                                <c:out value="${subjectDimension}" />
                            </div>
                            <br>
                            <%-- Submit form --%>
                            <div class="input-group">
                                <button type="submit" id="submit" class="btn btn-success">Change password!</button>
                                <input type="hidden" name="service" value="changePassword">
                            </div>
                            <%-- Display messages, if any --%>
                            <div>
                                <h4 style="color:red"> <c:out value="${message}"/> </h4>
                            </div>
                        </div>
                    </div>
                </form> 
            </div>

        </div>
        <%-- Include footer page --%>
        <jsp:include page="/jsp/footer.jsp"/>
    </body>
</html>

<script>
    <%-- Check if new password and re-enter password match or not --%>
    function () {
    }
</script>
