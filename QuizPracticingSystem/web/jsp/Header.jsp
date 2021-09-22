<%-- 
    Document   : Header
    Created on : Sep 20, 2021, 10:50:34 PM
    Author     : tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Page</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class="navbar-brand">
                    <img src="images/logo-removebg-preview.png" alt="" width="80" height="40"></a>
                <div class="collapse navbar-collapse" id="1">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="index.jsp" style="font-weight: bold;">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" style="font-weight: bold;">All Courses</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" style="font-weight: bold;">Blog</a>
                        </li>

                    </ul>
                    <form class="navbar-form navbar-right" role="search">
                        <div class="input-group">
                            <input type="text" class="form-control" style="border-radius: 5px" placeholder="">
                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-default">
                                    <span class="fas fa-search"></span>
                                </button>  
                            </span>
                        </div>
                    </form>

                    <form class="form-inline" style="float:right">
                        <a href="login/login.jsp" class="btn btn-default "style="background-color:#5BC0DE;color:white;font-weight: bold;" 
                           role="button" aria-pressed="true">Login</a>
                        <a href="#" class="btn btn-default " style="background-color:#5BC0DE;color:white;font-weight: bold;"
                           role="button" aria-pressed="true">Signup</a>
                    </form>
                </div>
            </div>
        </nav>


        <!--Jquery, JS-->
        <script src="https://kit.fontawesome.com/9650a62e47.js" crossorigin="anonymous"></script>
    </body>
</html>
