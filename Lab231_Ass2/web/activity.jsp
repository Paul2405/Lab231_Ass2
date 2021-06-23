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
    </head>
    <body>
        <div class="container">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-xs-8">
                                <h2><b>Activity History</b></h2>
                            </div>

                            <div class="col-xs-4">
                                <a href="load"><button type="button" class="btn btn-success">Dashboard</button></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Action</th>
                                <th>ByUser</th>						
                                <th>Email</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <c:if test="${not empty ACTIVITY}" var="testNull">
                            <tbody>
                                <c:forEach items="${ACTIVITY}" var="ac" varStatus="counter">
                                    <tr>
                                        <td>${ac.getActivity()}</td>
                                        <td>${ac.getName()}</td>                        
                                        <td>${ac.getEmail()}</td>
                                        <td>${ac.getDate()}</td>
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
    </body>
</html>
