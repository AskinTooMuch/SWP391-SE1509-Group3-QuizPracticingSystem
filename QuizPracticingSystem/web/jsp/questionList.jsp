<%-- 
    Document   : questionList
    Created on : Oct 5, 2021, 8:46:40 PM
    Author     : tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject List</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
        <script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" ></script>
        <link rel="stylesheet" href="${contextPath}/css/questionList.css">
    </head>
    <body>
        <div class="wrap">
            <%-- Include header page --%>
            <jsp:include page="header.jsp"/>
            <div class="row" style="margin: 10px;">
                <div class="col-md-2" id="form" style="height: 330px">
                    <h2 class="text-center">Filter</h2>
                    <div style="margin-bottom: 20px;">
                        <form action = "${contextPath}/quizController" class="navbar-form">
                            <div class="form-group">
                                <input  class="form-control" type="text" id="content" placeholder="Content... " name="content">
                                <button type="submit" class="btn btn-primary" style="width: 100%">Search</button>
                                <input type="hidden" name="service" value="searchQuestionByContent">
                            </div>
                        </form>
                        <button type="button" class="btn btn-info dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="width: 100%;">
                            Question By Subject
                        </button>
                        <div class="dropdown-menu" style="background-color: #fff; text-align: center">
                            <a class="dropdown-item" href="">
                                OOP with java</a>
                        </div>
                    </div>
                    <div style="margin-bottom: 20px;">
                        <button type="button" class="btn btn-info dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="width: 100%;">
                            Question By Lesson
                        </button>
                        <div class="dropdown-menu" style="background-color: #fff; text-align: center">
                            <a class="dropdown-item" href="">Introduction</a>
                        </div>
                    </div>
                    <div style="margin-bottom: 20px;">
                        <button type="button" class="btn btn-info dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="width: 100%;">
                            Question By Dimension
                        </button>
                        <div class="dropdown-menu" style="background-color: #fff; text-align: center">
                            <a class="dropdown-item" href="">
                                Java Programming </a>
                        </div>
                    </div>

                </div>
                <div class="col-md-1"></div>
                <div class="col-md-9" >
                    <table class="table table-fluid" style="margin-right: 10px;border: solid 1px;border-radius: 5px;">
                        <thead>
                            <tr><th>Id</th>
                                <th>Content</th>
                                <th>Subject</th>
                                <th>Lesson</th>
                                <th>Dimension</th>
                                <th>Status</th>
                                <th>Action</th></tr>
                        </thead>
                        <tbody>
                            <c:if test="${listQuestionManage!=null}">
                                <c:forEach items="${listQuestionManage}" var="questionList">
                                    <tr>
                                        <td><c:out value="${questionList.getQuestionId()}"/></td>
                                        <td><c:out value="${questionList.getContent()}"/></td>
                                        <td><c:out value="${questionList.getSubjectName()}"/></td>
                                        <td><c:out value="${questionList.getLessonName()}"/></td>
                                        <td><c:out value="${questionList.getDimensionName()}"/></td>
                                        <td><c:if test="${questionList.isStatus()}">
                                                Available
                                            </c:if>
                                            <c:if test="${!questionList.isStatus()}">
                                                Not Available
                                            </c:if>
                                          </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="space" style="min-height: 50vh;"></div>
            <%-- Include footer page --%>
            <jsp:include page="footer.jsp"/>
        </div>
        <script>
            
        </script>
    </body>
</html>
