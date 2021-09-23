<%-- 
    Document   : changePassword
    Created on : Sep 23, 2021, 10:08:58 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password Page</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
    </head>
    <body>
        <div class="container" style="align-self: center">
            <form action="userController" method="POST">
                <div class="row">
                    <div class="col-md-5">
                        <label class="label control-label">Old Password</label>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-user"></span></span>
                            <input type="text" class="form-control" name="oldPassword" placeholder="">
                        </div>
                        <label class="label control-label">New Password</label>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-lock"></span></span>
                            <input type="password" class="form-control" name="newPassword" placeholder="">
                        </div>
                        <label class="label control-label">Confirm Password</label>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-lock"></span></span>
                            <input type="password" class="form-control" name="rePassword" placeholder="">
                        </div>
                        <div class="input-group">
                            <button type="submit" class="btn btn-default">Change password!</button>
                            <input type="hidden" name="service" value="changePassword">
                        </div>
                    </div>
                </div>
            </form>
    </body>
</html>
