<%-- 
    Document   : BlogList
    Created on : Sep 21, 2021, 11:10:40 AM
    Author     : ADMN
--%>

<%@page import="bean.PostCate"%>
<%@page import="bean.Blog"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <form action="marketingController">
                        <div class="filters">
<!--                            searchBox-->
                            <div class="row">
                                <div class="searchBox"></div>
                            </div>
<!--                            fiters-->
                            <div>
                                <h6>Categories</h6>
                            </div>
                            <div class="row">
                                <% for (PostCate p : postCateList) { %>
                                <div class="col-6">
                                    <input type="checkbox" name="category" value="<%=p.getPostCateId()%>"/><%=p.getPostCateName()%>
                                </div>
                                <%}%>
                            </div>
                        </div>                
                        <input type="submit" value="Search"/>
                        <input type="hidden" value="blogDetail">
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
                    <c:set var="page" value="${requestScope.page}"/>  
                    <ul class="row pagination">
                        <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                            <li class="${i==page?"active":""}"><a  href="?page=${i}${requestScope.url}" >${i} </a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>


        </div>
    </div>
</body>
</html>
