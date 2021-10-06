<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 29, 2021, 9:32:27 AM
    courseContentDetail : display course detail - admin view
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    4/10/21     1.0         ChucNVHE150618  First Deploy
    5/10/21     2.0         ChucNVHE150618  Frontend and overview form
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
        <%-- Check If user is logged in or not, if not redirect to error page --%>
        <c:if test="${sessionScope.currUser == null}">
            <c:set var = "errorMess" scope="session" value = "User not logged in"/>
            <c:redirect url="/error.jsp"/>
        </c:if>

        <%-- Check If subject is available or not, if not redirect to error page --%>
        <c:if test="${empty subject}">
            <c:set var = "errorMess" scope="session" value = "Subject not available"/>
            <c:redirect url="/error.jsp"/>
        </c:if>

        <%-- Include header page --%>
        <jsp:include page="header.jsp"/>
        <%-- Main page --%>
        <div class="row">

            <div class="col-md-2"></div>

            <%-- Center form --%>
            <div class="col-md-8">
                <%-- Header nav tab --%>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="tab col-md-6">
                        <button class="tablinks active" onclick="openTab(event, 'tab1')">Overview</button>
                        <button class="tablinks" onclick="openTab(event, 'tab2')">Dimension</button>
                        <button class="tablinks" onclick="openTab(event, 'tab3')">Price Package</button>
                    </div>
                    <div class="col-md-3"></div>
                </div>
                <%-- Main tab details --%>
                <div class="details">
                    <div id="tab1" class="tabcontent" style="display: block">
                        <h4 style="color: #565e64">Subject Overview</h4>
                        <%-- Form details: The whole tab is a form with the subject's details as set values --%>
                        <form style="padding: 5px;">
                            <%-- First bootstrap form row: subject name, category, featured subject, status and thumbnail image --%>
                            <div class="form-row">
                                <div class="form-group col-md-7">
                                    <br>
                                    <label for="subjectName">Subject Name</label>
                                    <input type="text" class="form-control" id="inputSubjectName" value="${subject.getSubjectName()}" style="margin-bottom: 5px;">

                                    <label for="subjectCate">Category</label>
                                    <div class="dropdown">
                                        
                                        <button class="btn btn-default dropdown-toggle" type="button" 
                                                id="dropdownMenu1" data-toggle="dropdown" 
                                                aria-haspopup="true" aria-expanded="true"
                                                style="width: 100%; border: 1px solid #ced4da">
                                            <i class="fas fa-bars"></i>
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu checkbox-menu allow-focus" aria-labelledby="dropdownMenu1">
                                            <c:forEach items = "${categoryList}" var="category" begin = "0" end = "${categoryList.size()-1}">
                                                <li>
                                                    <label>
                                                        <input type="checkbox" checked> <c:out value="${category.getSubjectCateName()}"/>
                                                    </label>
                                                </li>
                                            </c:forEach>
                                            <c:forEach items = "${categoryRemainList}" var="category" begin = "0" end = "${categoryRemainList.size()-1}">
                                                <li>
                                                    <label>
                                                        <input type="checkbox"> <c:out value="${category.getSubjectCateName()}"/>
                                                    </label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>

                                    <br>
                                    <div class="form-row">
                                        <div class="form-group col-md-5">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="gridCheck" checked="${subject.isFeaturedSubject()}">
                                                <label class="form-check-label" for="gridCheck">
                                                    Featured Subject
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label for="inputState">Status</label>
                                        </div>
                                        <div class="form-group col-md-5" >
                                            <select id="inputState" class="form-control" style="border: 1px solid #ced4da">
                                                <option selected value="1">Available</option>
                                                <option value="0">Disabled</option>
                                            </select>
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group col-md-1"></div>
                                <div class="form-group col-md-4">
                                    <img src="${contextPath}/images/${subject.getThumbnail()}" style="height: 100%; width: 100%;">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputDescription">Description</label>
                                <textarea class="form-control" style="min-height: 4em; overflow: scroll;"><c:out value="${subject.getDescription()}"/></textarea>
                            </div>


                            <div class="form-row">
                                <div class="form-group" style="margin-right: 1em; margin-left: 1em;">
                                    <a href="${contextPath}/index.jsp" class="btn btn-primary">Submit</a>
                                </div>
                                <div class="form-group">
                                    <a href="${contextPath}/index.jsp" class="btn btn-primary">Back</a>
                                </div>
                            </div>
                        </form>
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
            </div>

            <div class="col-md-2"></div>
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

        $(".checkbox-menu").on("change", "input[type='checkbox']", function () {
            $(this).closest("li").toggleClass("active", this.checked);
        });

        $(document).on('click', '.allow-focus', function (e) {
            e.stopPropagation();
        });
    </script>
</html>
