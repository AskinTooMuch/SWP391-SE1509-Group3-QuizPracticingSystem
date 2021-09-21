<%-- 
    Document   : blogList
    Created on : Sep 21, 2021, 11:10:40 AM
    Author     : ADMN
--%>

<%@page import="bean.PostCate"%>
<%@page import="bean.Blog"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%  ArrayList<Blog> blogList = (ArrayList<Blog>) request.getAttribute("blogList");  %>
        <%  ArrayList<PostCate> postCateList = (ArrayList<PostCate>) request.getAttribute("postCateList");  %>
        <div class="contaier">
            <div class="Title row">
                <div class="col-12">
                    <h5>ALL BLOG</h5>
                </div>
            </div>

            <div class="row">
                <div class="left col-3">
                    <!--                            searchBox-->
                    <div class="row">
                        <div class="searchBox">
                            <form action="search">
                                <input name="searchstring" type="text" class="searchbar form-control rounded" placeholder="Search" style=""/>
                                <input type="image" name="submit" src="images/search.png" border="0" style="width: 40px; height: 40px;">                     
                            </form>
                        </div>
                    </div>
                    <form action="marketingController">
                        <!--                        fiters-->
                        <div>
                            <h6>Categories</h6>
                        </div>
                        <div class="row">
                            <% for (PostCate p : postCateList) {%>
                            <div class="col-6">
                                <input type="checkbox" name="category" value="<%=p.getPostCateId()%>"/><%=p.getPostCateName()%>
                            </div>
                            <%}%>
                        </div>

                        <input type="submit" value="Search"/>
                        <input type="hidden" value="blogList"/>
                    </form>
                    <!--                        blog list-->
                </div>
                <div class="rigt col-9">
                    <%for (Blog blog : blogList) {%>
                    <div class="col-sm-4 col-md-4">            
                        <a href="marketingController?service=blogDetail"> <img src="<%=blog.getThumbnail()%>"></a>
                        <span class="cardname"><%=blog.getBlogTitle()%></span>
                        <div class=""> <a class="detail" href="marketingController?service=blogDetail&blogId=<%=blog.getBlogId()%>">READ</a></div>
                    </div>
                    <%}%>

                    <div class="pagination">
                        <c:set var="page" value="${requestScope.page}"/> 
                        <c:forEach begin="1" end="${requestScope.pagenum}" var="i"> 
                            <a clas class="${page==i?"active":""} paging" href="marketingController?service=blogList${alterUrl}&page=${i}">${i}</a>                         
                        </c:forEach> 
                    </div>
                </div>
            </div>


        </div>
    </div>
</body>
</html>
