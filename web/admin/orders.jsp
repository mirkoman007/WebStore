<%-- 
    Document   : orders
    Created on : 25.01.2022., 21:24:01
    Author     : mirko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="dtf" uri="http://mirkozaper.from.hr/jsp/jctl/dtf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../head.jsp" %>
    </head>
    <body>
        <jsp:include page="../header.jsp?page=admin"/>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div class="list-group">
                        <a href="./categories.jsp" class="list-group-item list-group-item-action">Categories</a>
                        <a href="./products.jsp" class="list-group-item list-group-item-action">Products</a>
                        <a href="./logs.jsp" class="list-group-item list-group-item-action">Login logs</a>
                        <a href="./orders.jsp" class="list-group-item list-group-item-action active">Orders</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <form method="get" class="input-group mb-3">
                        <input type="text" name="username" class="form-control" placeholder="Username">
                        <input type="date" name="dateFrom" class="form-control">
                        <input type="date" name="dateTo" class="form-control">
                        <button class="btn btn-outline-secondary" type="submit">Search</button>
                    </form>
                    <div class="table-responsive scrollbar">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Username</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${orders}" var="item">
                                    <tr>
                                        <th scope="row">${item.id}</th>
                                        <td>${item.username}</td>
                                        <td><dtf:date datetime="${item.dateTime}"/></td>
                                        <td><dtf:time datetime="${item.dateTime}"/></td>
                                        <td class="text-end">
                                            <div>
                                                <a class="btn p-0" type="button" data-bs-toggle="tooltip" data-bs-placement="top" title="Edit" href="../order.jsp?id=${item.id}">
                                                    <span class="text-500 fas fa-info-circle"></span>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
            </div>
        </div>
    </body>
</html>
