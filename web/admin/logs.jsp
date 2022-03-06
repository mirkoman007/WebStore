<%-- 
    Document   : logs
    Created on : 25.01.2022., 21:23:50
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
                        <a href="./logs.jsp" class="list-group-item list-group-item-action active">Login logs</a>
                        <a href="./orders.jsp" class="list-group-item list-group-item-action">Orders</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="table-responsive scrollbar">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Username</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Time</th>
                                    <th scope="col">IP address</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                                <c:forEach items="${loginLogs}" var="log">
                                    <tr>
                                        <th scope="row">${log.id}</th>
                                        <td>${log.username}</td>
                                        <td><dtf:date datetime="${log.dateTime}"/></td>
                                        <td><dtf:time datetime="${log.dateTime}"/></td>
                                        <td>${log.ipAddress}</td>
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
