<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 29, 2021, 9:32:27 AM
    courseContentDetail : display course detail - admin view
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    4/10/21     1.0         ChucNVHE150618  First Deploy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject List</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="${contextPath}/css/courseContentDetail.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    
    </head>
    <body>
        <%-- Include header page --%>
        <jsp:include page="header.jsp"/>
        <div class="main" style="min-height: 55vh">
            
            
            <div class="tab">
                <button class="tablinks" onclick="openTab(event, 'tab1')">Tab 1</button>
                <button class="tablinks" onclick="openTab(event, 'tab2')">Tab 2</button>
                <button class="tablinks" onclick="openTab(event, 'tab3')">Tab 3</button>
            </div>

            <div id="tab1" class="tabcontent" style="display: block">
              <h3>Tab 1</h3>
              <p>Tab content 1</p>
            </div>

            <div id="tab2" class="tabcontent">
              <h3>Tab 2</h3>
              <p>Tab content 2</p> 
            </div>

            <div id="tab3" class="tabcontent">
              <h3>Tab 3</h3>
              <p>Tab content 3</p>
            </div>
            
        </div>
        <%-- Include footer page --%>
        <jsp:include page="footer.jsp"/>
    </body>
    
    <script>
        <%-- Javascript for tabs opening --%>
        function openTab(evt, tabName) {
          var i, tabcontent, tablinks;
          tabcontent = document.getElementsByClassName("tabcontent");
          for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
          }
          tablinks = document.getElementsByClassName("tablinks");
          for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
          }
          document.getElementById(tabName).style.display = "block";
          evt.currentTarget.className += " active";
        }
</script>
</html>
