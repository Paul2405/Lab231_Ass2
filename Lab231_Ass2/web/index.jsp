<%-- 
    Document   : index
    Created on : Jun 5, 2021, 11:05:36 PM
    Author     : haudq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <title>JSP Page</title>
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="sidenav">
            <div class="login-main-text">
                <h2>User Management<br></h2><br><br><h4>Login Page</h4>
                <p>Login from here to access.</p>
            </div>
        </div>
        <div class="main">
            <div class="col-md-6 col-sm-12">
                <div class="login-form">
                    <form action="login" method="Post">
                        <div class="form-group">
                            <label>User Id</label>
                            <input name="txtUsername" type="text" class="form-control" placeholder="User Id">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input name="txtPassword" type="password" class="form-control" placeholder="Password">
                        </div>
                        <button type="submit" class="btn btn-black">Login</button><br>
                        <span style="color: red">${ERROR}</span>

                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
