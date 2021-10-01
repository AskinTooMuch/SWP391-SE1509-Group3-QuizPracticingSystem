<!--
   Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
   Created on : Sep 23, 2021
   QuizController map
   Quiz practicing system
 
   Record of change:
   Date        Version     Author          Description
   23/9/21     1.0         NamDHHE150519   First Deploy
   23/9/21     2.9         NamDHHE150519   complete all funtion
-->

<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        <jsp:include page="header.jsp"/>

        <div class="container-fluid" style="border-top: 1px black solid;">
            <div class="row">
                <div class="left col-3" style="margin-top:42px; border-right: 1px black solid;">
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
                                <input class="btn btn-primary" type="submit" value="Search"/>
                                <input hidden name="service" value="blogList"/>
                            </div>
                        </form>
                    </div>

                    <div style="margin-top: 20px;">
                        <a href="marketingController?service=blogList"><h5>Lastest Posts</h5></a>
                        <% BlogINT blogDAO = new BlogDAO();
                            ArrayList<Blog> lastBlogs = (ArrayList<Blog>) request.getAttribute("lastBlogs");
                            for (Blog blog : lastBlogs) {
                        %>
                        <div class="lastposts" style=" display: flex;margin-top: 20px; border: 1px #bccafd solid;" >
                            <div style="width: 104px;border-right:#bccafd 1px solid;">
                                <img src="<%=blog.getThumbnail()%>" style="height: 70px;width: auto; ">
                            </div>
                            <div>
                                <a style="text-decoration: none;color: black;" href="marketingController?service=blogDetail&blogId=<%=blog.getBlogId()%>"><h6><%=blog.getBlogTitle()%></h6></a>
                            </div>
                        </div>
                        <%}%>
                    </div>
                </div>

                <div class="right col-9">
                    <%
                        Blog blog = (Blog) request.getAttribute("blog");
                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = blog.getLastEdited();
                        
                    %>
                    <div class="info row">
                        <div class="cate col-9"><%=blogDAO.getBlogCategory(blog.getBlogId()).getPostCateName()%></div>
                        <div class="date col-3"><div style="float:right;">Last Edited: <%=df.format(date)%></div></div>
                    </div>        
                    <div class="title row">
                        <h4>${blog.getBlogTitle()}</h4>
                    </div>
                    <hr>
                    <div class="content row" style="text-align: justify;">
                        <p><%=blog.getDetail()%></p>
                    </div>
                    <div class="author row" style="float:right;">
                        <div>
                            <div style="float:right;">Author</div>                        
                        </div>
                        <div>
                            <div style="float:right;">
                                <h5><%=blogDAO.getAuthor(blog.getBlogId()).getUserName()%></h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <jsp:include page="footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
