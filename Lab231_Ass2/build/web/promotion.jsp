<%-- 
    Document   : promotion
    Created on : Jun 11, 2021, 10:03:36 AM
    Author     : haudq
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link href="css/admin.css" rel="stylesheet" type="text/css"/>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-xs-4">
                                <h2><b>Promotion User Management</b></h2>
                            </div>
                           
                            <div class="col-xs-8">
                                <a href="load"><button type="button" class="btn btn-success">Confirm</button></a>
                                <form action="promotion" method="POST">
                                    <input type="hidden" name="btnAction" value="all"/>
                                    <button type="submit" name="action" value="delete" class="btn btn-danger">Delete</button>
                            </div>
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
                                <th>Rank</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <c:if test="${not empty USER}" var="testNull">
                            <tbody>
                                <c:forEach items="${USER}" var="user" varStatus="counter">
                                    <tr>
                                        <td><c:if test="${user.getStatus().trim() != 'inactive'}">
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
                                        <td>${user.getRank()}</td>
                                        <td>
                                            <c:if test="${user.getStatus().trim() != 'inactive'}">
                                                <a href="#"data-toggle="modal" data-target="#exampleModal" data-whatever="${user.getId()};${user.getRank()}"><i class="fa fa-edit"></i></a>
                                                </c:if>
                                        </td>
                                    </tr>
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
        <!--modal-->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <form action="promotion" method="POST">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Update Rank</h5>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Rank</label>
                            <input type="number" min="1" max="10" name="rank" class="form-control" id="rank">
                            <input type="hidden" id="id" value="" name="id"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" value="change" name="action" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
                </form>
            </div>
        </div>

        <script>
            $('#exampleModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget);
                var recipient = button.data('whatever');
                var id , rank = '';
                recipient.split(';');
                for (var i = 0, max = recipient.length; i < max; i++) {
                    if(i === 0 ){
                        id = recipient[i];
                    }
                    if( i > 1){
                        rank += recipient[i];
                    }
                }
                var modal = $(this);
                modal.find('#rank').val(rank);
                modal.find('#id').val(id);
            })
        </script>
    </body>
</html>
