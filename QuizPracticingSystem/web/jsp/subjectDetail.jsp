<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Nov,3 2021, 11:46:32 PM
    Display subject list in cards
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    3/10/21     1.0         TungBTHE150621  First Deploy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${subject.getSubjectName()}</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
/* Popup container - can be anything you want */
.popup {
  position: relative;
  display: inline-block;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* The actual popup */
.popup .popuptext {
  visibility: hidden;
  width: 160px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 8px 0;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  margin-left: -80px;
}

/* Popup arrow */
.popup .popuptext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

/* Toggle this class - hide and show the popup */
.popup .show {
  visibility: visible;
  -webkit-animation: fadeIn 1s;
  animation: fadeIn 1s;
}

/* Add animation (fade in the popup) */
@-webkit-keyframes fadeIn {
  from {opacity: 0;} 
  to {opacity: 1;}
}

@keyframes fadeIn {
  from {opacity: 0;}
  to {opacity:1 ;}
}
</style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="container-fluid" style="border-top: 1px black solid;">
            <h2>Popup</h2>

<div class="popup" onclick="myFunction()">Click me to toggle the popup!
  <span class="popuptext" id="myPopup">A Simple Popup!</span>
</div>

<script>
// When the user clicks on div, open the popup
function myFunction() {
  var popup = document.getElementById("myPopup");
  popup.classList.toggle("show");
}
</script>
            <div class="row">
                <div class="left col-3" style="margin-top:42px; border-right: 1px black solid;">
                    <div>
                        <form action="marketingController" method="">
                            <div class="filter" style="">      
                                <input type="text" name="search" class="form-control" style="border-radius: 5px" placeholder="Search Subject">
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
                        <a href="marketingController?service=blogList"><h5>Latest Posts</h5></a>
                        <c:forEach items="${lastBlogs}" var="blog">
                            <div class="lastposts" style=" display: flex;margin-top: 20px; border: 1px #bccafd solid;" >
                                <div style="width: 104px;border-right:#bccafd 1px solid;">
                                    
                                </div>
                                <div>
                                    <a style="text-decoration: none;color: black;" href="marketingController?service=blogDetail&blogId=${blog.getBlogId()}"><h6>${blog.getBlogTitle()}</h6></a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="right col-9">
                    <c:set var="thisSubject" value="${subject}"/>
                    <div class="info row">
                        <div class="cate col-9">${subject.getSubjectName()}</div>
                        
                    </div>        
                    <div class="title row">
                        <h4>${subject.getSubjectName()}</h4>
                    </div>
                    <hr>
                    <div class="content row" style="text-align: justify;">
                        
                        <p>${subject.getDescription()}</p>
                    </div>
                    <div class="author row" style="float:right;">
                        <div>
                            <div style="float:right;">Author</div>                        
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
