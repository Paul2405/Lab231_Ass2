<%-- 
    Document   : index
    Created on : May 10, 2021, 3:53:54 PM
    Author     : haudq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new account</title>
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/create.css" rel="stylesheet" type="text/css"/>
        <script>

        </script>
    </head>
    <body>
        <div class="container register">
            <div class="row">
                <div class="col-md-3 register-left">
                    <img src="https://www.clipartmax.com/png/full/199-1998454_role-icon-role-based-access-control-icon.png" style="height: 150px;width: 150px;" class="avatar" alt="Avatar"><br>
                    <a href="load?btnAction=all"><input type="submit" name="" value="Back to dashboard"/><br/></a>
                </div>
                <div class="col-md-9 register-right">
                    <form action="create" method="POST" enctype="multipart/form-data">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <h3 class="register-heading">Create account </br>
                                    <span style="color: red; font-size: small">${ERROR}</span><br>
                                </h3>

                                <div class="row register-form">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="userId" placeholder="UserId *"  required/>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control" id="password" name="pass" placeholder="Password *" required=""/>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control" id="confirmPass" name="confirmPass" placeholder="Confirm Password *"   required="" />
                                            <span id="errorConfirm"></span>
                                        </div>
                                        <div class="form-group">
                                            <select name="role">
                                                <option value="admin" selected="">admin</option>
                                                <option value="user">user</option>
                                            </select><br>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="email" class="form-control" name="email" placeholder="Your email *"  required=""/>
                                        </div>

                                        <div class="form-group">
                                            <input type="text" pattern="[0-9]{10}" title="The phone must be 10 digits"  name="phone" class="form-control" placeholder="Your Phone *" required="" />
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="name" placeholder="Name *" required=""/>
                                        </div>

                                        <div class="form-group">
                                            <label for="fileName">Avatar: </label> 
                                            <input id="fileName" type="file" name="fileName" size="30" /><br />
                                        </div>
                                        <input type="submit" class="btnRegister"  value="Create"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>