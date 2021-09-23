<%-- 
    Document   : blogDetail
    Created on : Sep 23, 2021, 8:57:01 AM
    Author     : ADMN
--%>

<%@page import="bean.Blog"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.BlogINT"%>
<%@page import="dao.impl.BlogDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${blog.getBlogTitle()}</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="Header.jsp"/>

        <div class="container-fluid" style="height:500px">
            <div class="row">
                <div class="left col-3" style="margin-top:42px;">
                    <div>
                        <form action="marketingController" method="">
                            <div class="filter" style="">      
                                <input type="text" name="search" class="form-control" style="border-radius: 5px" placeholder="Search in BlogList">
                                <div>
                                    <h5>Category</h5></div>
                                <div>
                                    <c:forEach items="${requestScope.postCateList}" var="category">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="category" value="${category.getPostCateId()}">
                                            <label class="form-check-label" for="flexCheckDefault">
                                                ${category.getPostCateName()}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                                <input type="submit" value="Search"/>
                                <input hidden name="service" value="blogList"/>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="right col-9">
                    <% BlogINT blogDAO = new BlogDAO();
                        Blog blog = (Blog) request.getAttribute("blog");
                    %>
                    <div class="info row">
                        <div class="cate col-9"><%=blogDAO.getBlogCategory(blog.getBlogId()).getPostCateName()%></div>
                        <div class="date col-3">Last Edited: <%=blog.getLastEdited()%></div>
                    </div>        
                    <div class="title row">
                        <h4>${blog.getBlogTitle()}</h4>
                    </div>
                    <hr>
                    <div class="content row">
                        <p><%=blog.getDetail()%></p>
                    </div>
                    <div class="author row" style="float:right;">Author<span><h5><%=blogDAO.getAuthor(blog.getBlogId()).getUserName()%></h5></span></div>
                </div>
            </div>
                </div>

    </body>
    <jsp:include page="Footer.jsp"/>
</html>
