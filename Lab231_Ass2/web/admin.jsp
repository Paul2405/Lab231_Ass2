<%-- 
    Document   : error
    Created on : Jun 8, 2021, 12:46:43 AM
    Author     : haudq
--%>

<%@page import="java.util.List"%>
<%@page import="haudq.dto.UserDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search page</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link href="css/admin.css" rel="stylesheet" type="text/css"/>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
        <div class="container">
            <h2>User Management</h2>
            <p>Make it easy to manage human resource.</p><br>
            <span>Welcome ${INFO.getName()}</span> <br><a href="logout">logout </a>&#124<c:if test="${INFO.getRole().trim() == 'admin'}"><a href="loadPromotion"> Promotion List</a></c:if>&#124<a href="activity?id=${INFO.getId()}"> Activity history</a><br/><br/>
                <ul class="nav nav-tabs">
                    <li><a href="load?btnAction=all">All</a></li>
                    <li class="active"><a data-toggle="tab" href="load?btnAction=admin">Admin</a></li>
                    <li><a  href="load?btnAction=user">User</a></li>
                </ul>

                <div class="tab-content">
                    <div id="tab" class="tab-pane fade in active">
                        <div class="container">
                            <div class="table-responsive">
                                <div class="table-wrapper">
                                    <div class="table-title">
                                        <div class="row">
                                            <div class="col-xs-4">
                                                <h2><b>All User Management</b></h2>
                                            </div>
                                        <c:if test="${INFO.getRole().trim() == 'admin'}">
                                            <div class="col-xs-4">
                                                <form action="search" method="POST">
                                                    <div class="input-group">
                                                        <input type="text" name="txtSearch" class="form-control" placeholder="Search">
                                                        <input type="hidden" name="btnAction" value="admin"/>
                                                        <div class="input-group-btn">
                                                            <button class="btn btn-default" type="submit">
                                                                <i class="glyphicon glyphicon-search"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="col-xs-4">
                                                <a href="create.jsp"><button type="button" class="btn btn-success">Add new +</button></a>
                                                <form action="delete" method="POST">
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                    <input type="hidden" name="btnAction" value="admin"/>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <table class="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Avatar</th>
                                            <th>Name</th>						
                                            <th>UserId</th>
                                            <th>Role</th>
                                            <th>Email</th>
                                            <th>Phone</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <c:if test="${not empty USER }" var="testNull">
                                        <tbody>
                                            <c:forEach items="${USER}" var="user" varStatus="counter">
                                                <c:if test="${user.getRole().trim() == 'admin'}">
                                                    <tr>
                                                        <td><c:if test="${user.getStatus().trim() != 'inactive' and INFO.getRole().trim() == 'admin'}">
                                                                <input type="checkbox" name="listUserDelete" value="${user.getId()}"/>
                                                            </c:if></td>
                                                        <td>
                                                            <img src="${user.getAvatar()}" style="height: 150px;width: 150px;" class="avatar" alt="Avatar"><br>
                                                        </td>
                                                        <td> ${user.getName()}</td>
                                                        <td>${user.getUserId()}</td>                        
                                                        <td>${user.getRole()}</td>
                                                        <td>${user.getEmail()}</td>
                                                        <td>${user.getPhone()}</td>
                                                        <td><span class="status text-success">&bull;</span> ${user.getStatus()}</td>
                                                        <td>
                                                            <c:if test="${user.getStatus().trim() != 'inactive' and INFO.getRole().trim() == 'admin'}">
                                                                <a href="update?id=${user.getId()}"><i class="fa fa-edit"></i></a>
                                                                <a href="promotion?id=${user.getId()}&action=add"><i class="fa fa-plus"></i></a>
                                                                </c:if>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </c:if>
                                    <c:if test="${!testNull}">
                                        <h2>Data can not found!</h2>
                                    </c:if>
                                </table>
                                </form>
                            </div>
                        </div>        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
