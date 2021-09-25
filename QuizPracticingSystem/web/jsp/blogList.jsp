<%-- 
    Document   : blogList
    Created on : Sep 21, 2021, 11:10:40 AM
    Author     : ADMN
--%>

<%@page import="bean.PostCate"%>
<%@page import="bean.Blog"%>
<%@page import="dao.BlogINT"%>
<%@page import="dao.impl.BlogDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog List</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            .body{
                font-family: Source Serif Pro;
            }
        </style>
    </head>

    <body>

        <jsp:include page="Header.jsp"/>
        <hr>

        <div class="container-fluid">
            <div class="row">
                <div class="left col-3" style="margin-top:42px; border-right: 1px solid black;">
                    <div>
                        <form action="marketingController" method="">
                            <div class="filter" style="">      
                                <input type="text" name="search" class="form-control" style="border-radius: 5px" placeholder="Search in BlogList">
                                <div>
                                    <h5>Category</h5>
                                </div>
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
                                <input type="submit" class="btn btn-primary" value="Search"/>
                                <input hidden name="service" value="blogList"/>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="right col-9">
                    <div style="margin-left:9%;">
                        <div class ="row">
                            <div>
                                <a href="marketingController?service=blogList" style="text-decoration:none;color:black;"><h3 class="head col-3">ALL BLOGS</h3></a>
                            </div>             
                        </div>
                        <div class="blogList row">
                            <% BlogINT blogDAO = new BlogDAO();
                                ArrayList<Blog> blogList = (ArrayList<Blog>) request.getAttribute("blogList");
                                for (Blog blog : blogList) {
                            %>
                            <div class="card" style="width: 18rem;margin-bottom: 20px;">
                                <img class="card-img-top" src="<%=blog.getThumbnail()%>" style="width:100%;height:270px;" alt="Card image cap">
                                <div class="card-body">
                                    <h8>Author <%=blogDAO.getAuthor(blog.getBlogId()).getUserName()%></h8>
                                    <h5 class="card-title"><%=blog.getBlogTitle()%></h5>
                                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                                    <a href="marketingController?service=blogDetail&blogId=<%=blog.getBlogId()%>" class="btn btn-primary">Read More</a>
                                </div>
                            </div>
                            <%}%>
                            <style>
                                .card-body{
                                   height: 300px;
                                }
                                .card-body .btn{
                                    position:absolute;
                                    bottom:20px;
                                    left: 85px;
                                }
                            </style>
                        </div>
                        <c:set var="page" value="${requestScope.page}"/>  
                        <ul class="pagination" style="margin:auto;">
                            <c:forEach begin="${1}" end="${requestScope.pagenum}" var="i">
                                <li class="${i==page?"active":""}"><a  href="?service=blogList&page=${i}${requestScope.pagingUrl}">${i} </a></li>
                                </c:forEach>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
        <style>


            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
            }
            .pagination .active{

                background-color: #4CAF50;
                color: white;

            }
            .pagination{
                justify-content: center;
                align-items: center;
            }
            .pagination a:hover:not(.active) {background-color: #ddd;}
        </style>

    </body>

    <jsp:include page="Footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

</html>
