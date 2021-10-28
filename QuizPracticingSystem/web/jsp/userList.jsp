<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Oct 28, 2021, 11:02:26 AM
    Display User list in cards
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    28/10/21     1.0         ChucNVHE150618  First Deploy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
        <link rel="stylesheet" href="${contextPath}/css/userList.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>
    <body>
        <%-- Check If user is logged in or not, if not redirect to error page --%>
        <c:if test="${(sessionScope.currUser == null)||(!sessionScope.role.getUserRoleName().equalsIgnoreCase('Admin'))}">
            <c:set var = "errorMess" scope="session" value = "User not logged in"/>
            <c:redirect url="/error.jsp"/>
        </c:if>

        <div class="wrap">
            <%-- Include header page --%>
            <jsp:include page="header.jsp"/>
            <div class="main">
                <c:choose>
                    <%-- Case 1: subjectList is empty --%>
                    <c:when test = "${empty userPageList}">
                        <c:redirect url="userList"/>
                    </c:when>
                    <%-- Case 2: subjectList is not empty --%>
                    <c:otherwise>
                        <%--If page is null, set default is 1--%>
                        <c:if test="${empty page}"><c:set var="page" value="1"/></c:if>
                        <%--If max page is null, redirect to servlet subjectList--%>
                        <c:if test="${empty maxPage}"><c:redirect url="${contextPath}/subjectList"/></c:if>
                            <div class="row" style="margin-top: 3rem">
                                <div class="col-md-1"></div>
                                <div class="col-md-2" id="form" style="height: 480px">
                                    <h2 class="text-center">Filter</h2>
                                    <div style="margin-bottom: 20px;">
                                    <%-- Start search form --%>
                                    <%--By name--%>
                                    <form action = "${contextPath}/userList" class="navbar-form">
                                        <label>By Name</label><br>
                                        <div class="input-group">
                                            <input  class="form-control" type="text" id="content" placeholder="Name..." name="criteria"  style="display: inline-block">
                                            <span class="input-group-btn">
                                                <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                            </span>
                                            <input type="hidden" name="criteriaType" value="userName">
                                        </div>
                                    </form>
                                    <%--By user Mail--%>
                                    <form action = "${contextPath}/userList" class="navbar-form">
                                        <label>By Mail</label>
                                        <div class="input-group">
                                            <br>
                                            <input  class="form-control" type="text" id="content" placeholder="Mail..." name="criteria"  style="display: inline-block">
                                            <span class="input-group-btn">
                                                <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                            </span>
                                            <input type="hidden" name="criteriaType" value="userMail">
                                        </div>
                                    </form>
                                    <%--By User Mobile--%>
                                    <form action = "${contextPath}/userList" class="navbar-form">
                                        <label>By Mobile</label>
                                        <div class="input-group">
                                            <br>
                                            <input  class="form-control" type="text" id="content" placeholder="Mobile..." name="criteria"  style="display: inline-block">
                                            <span class="input-group-btn">
                                                <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                            </span>
                                            <input type="hidden" name="criteriaType" value="userMobile">
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="col-md-8" id="form" style="min-height: 480px; min-width: 1000px">
                                <div class="container" >
                                    <%-- Table Container --%>
                                    <div class="form-group">
                                        <a href="#"><button class="btn btn-success" style="float:right;margin: 5px">Add new User</button></a>
                                    </div>
                                    <%-- Table of User List--%>
                                    <table id="table-id" class="table table-bordered table-striped">
                                        <thead>
                                            <%-- Headers of Table--%>
                                            <tr style="background-color: #F0D8D5;">
                                                <th>User Id</th>
                                                <th>User Name</th>
                                                <th>Gender</th>
                                                <th>Email</th>
                                                <th>Mobile</th>
                                                <th>Role</th>
                                                <th>Status</th>
                                                <th>Manage</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:choose>
                                                <c:when test="${empty userPageList}">
                                                    <tr style="color: red"><td colspan="8">No User Found</td></tr>
                                                </c:when>                               
                                                <c:otherwise>
                                                    <c:forEach items="${userPageList}" var="userList">
                                                        <tr>
                                                            <td><c:out value="${userList.getUserId()}"/></td>
                                                            <td><c:out value="${userList.getUserName()}"/></td>
                                                            <%-- Check gender--%>
                                                            <td><c:if test="${userList.isGender()}">
                                                                    Male
                                                                </c:if>
                                                                <c:if test="${!userList.isGender()}">
                                                                    Female
                                                                </c:if>
                                                            </td>
                                                            <td><c:out value="${userList.getUserMail()}"/></td>
                                                            <td><c:out value="${userList.getUserMobile()}"/></td>
                                                            <td><c:out value="${userList.getUserRole().getUserRoleName()}"/></td>
                                                            <%--Check if user status--%>
                                                            <td><c:if test="${userList.isStatus()}">
                                                                    Available
                                                                </c:if>
                                                                <c:if test="${!userList.isStatus()}">
                                                                    Not Available
                                                                </c:if>
                                                            </td>
                                                            <td><a href="#"><div class="btn btn-success">Edit</div></a></td>
                                                        </tr>
                                                    </c:forEach> 
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${message != null}">
                                                <tr style="color: red"><td colspan="8"><c:out value="${message}" /></td></tr>
                                            </c:if>
                                        </tbody>
                                    </table>
                                    <%--Start Pagination --%>
                                    <div class='pagination-container row'>
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination justify-content-center">
                                                <%--Previous Page--%>
                                                <c:choose>
                                                    <c:when test="${page > 1}">
                                                        <li class="page-item" id="previousPage">
                                                            <a class="page-link" href="${contextPath}/userList?page=${page-1}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li class="page-item disabled">
                                                            <a class="page-link" href="${contextPath}/userList?page=${page-1}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                                <%--2 buttons before active page--%>
                                                <c:forEach var="pageNumber" begin="1" end="${page-1}">
                                                    <li class="page-item" id="page${page-pageNumber}"><a class="page-link" href="${contextPath}/userList?page=${page-pageNumber}">${page-pageNumber}</a></li>
                                                    </c:forEach>
                                                    <%--Active page--%>
                                                <li class="page-item active" id="page${page}"><a class="page-link" href="#">${page}</a></li>
                                                    <%--2 buttons after active page--%>
                                                    <c:choose>
                                                        <c:when test="${maxPape-page >= 2}">
                                                            <c:forEach var="pageNumber" begin="1" end="2">
                                                            <li class="page-item" id="page${page+pageNumber}"><a class="page-link" href="${contextPath}/userList?page=${page+pageNumber}">${page+pageNumber}</a></li>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach var="pageNumber" begin="1" end="${maxPage - page}">
                                                            <li class="page-item" id="page${page+pageNumber}"><a class="page-link" href="${contextPath}/userList?page=${page+pageNumber}">${page+pageNumber}</a></li>
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>

                                                <%--Next Page--%>
                                                <c:choose>
                                                    <c:when test="${page == maxPage}">
                                                        <li class="page-item disabled">
                                                            <a class="page-link" href="${contextPath}/userList?page=${page+1}" aria-label="Next">
                                                                <span aria-hidden="true">&raquo;</span>
                                                                <span class="sr-only">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li class="page-item">
                                                            <a class="page-link" href="${contextPath}/userList?page=${page+1}" aria-label="Next">
                                                                <span aria-hidden="true">&raquo;</span>
                                                                <span class="sr-only">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </ul>
                                        </nav>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <%-- Include footer page --%>
            <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>

